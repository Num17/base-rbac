package com.base.config.security;

import com.base.config.handler.SecurityConstant;
import com.base.domain.Resource;
import com.base.domain.RoleResource;
import com.base.redis.RedisService;
import com.base.service.ResourceService;
import com.base.service.RoleResourceService;
import com.base.util.CollectionUtil;
import com.base.util.JsonUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final String URL_KEY = "url:role";
    private ResourceService resourceService;
    private RoleResourceService roleResourceService;
    private RedisService redisService;

    public UrlFilterInvocationSecurityMetadataSource(
            ResourceService resourceService,
            RoleResourceService roleResourceService, RedisService redisService) {
        this.resourceService = resourceService;
        this.roleResourceService = roleResourceService;
        this.redisService = redisService;
        loadResourceDefine();
    }

    // 在Web服务器启动时，提取系统中的所有权限
    private void loadResourceDefine() {
        //应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
        List<Resource> allResource = resourceService.getAllResource();
        Set<String> resourceIds = allResource.stream().map(Resource::getResourceId).collect(Collectors.toSet());
        List<RoleResource> roleResources = roleResourceService.getRoleResourceByResourceIds(resourceIds);
        Map<String, List<RoleResource>> resourceIdListMap = roleResources.stream().collect(Collectors.groupingBy(RoleResource::getResourceId));

        final Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();
        final Map<String, String> map = new HashMap<>();

        for (Resource resource : allResource) {
            String url = resource.getUrl();

            List<RoleResource> roleResourceList = resourceIdListMap.get(resource.getResourceId());

            if (!CollectionUtil.isEmpty(roleResourceList)) {

                if (!resourceMap.containsKey(url) || resourceMap.get(url) == null) {
                    resourceMap.put(url, new LinkedList<>());
                }

                Collection<ConfigAttribute> configAttributes = resourceMap.get(url);

                for (RoleResource roleResource : roleResourceList) {
                    configAttributes.add(new SecurityConfig(SecurityConstant.ROLE_SUFFIX + roleResource.getRoleId()));
                }

                map.put(url, JsonUtil.toJson(configAttributes));

            }

        }

        redisService.hashSetFromMap(URL_KEY, map);

    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        Map<String, String> resourceMap = redisService.hashEntries(URL_KEY);

        Iterator<String> iterator = resourceMap.keySet().iterator();
        Collection<ConfigAttribute> configAttributes = Collections.emptyList();
        while (iterator.hasNext()) {
            String url = iterator.next();

            if (antPathMatcher.match(url, requestUrl)) {
                String json = redisService.hashGet(URL_KEY, url);
                List<SecurityConfig> securityConfigs = JsonUtil.parseJsonArray(json, SecurityConfig.class);
                configAttributes = securityConfigs.stream().map((e) -> (ConfigAttribute) e).collect(Collectors.toList());
                break;
            }
        }

        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> configAttributes = new HashSet<>();

        List<String> list = redisService.hashValues(URL_KEY);
        list.forEach((e) -> configAttributes.addAll(JsonUtil.parseJsonArray(e, SecurityConfig.class)));

        return configAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
