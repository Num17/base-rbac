package com.base.config.security;

import com.base.domain.Resource;
import com.base.domain.RoleResource;
import com.base.service.ResourceService;
import com.base.service.RoleResourceService;
import com.base.util.CollectionUtil;
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

    private ResourceService resourceService;
    private RoleResourceService roleResourceService;
    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

    public UrlFilterInvocationSecurityMetadataSource(ResourceService resourceService, RoleResourceService roleResourceService) {
        this.resourceService = resourceService;
        this.roleResourceService = roleResourceService;
        loadResourceDefine();
    }

    // 在Web服务器启动时，提取系统中的所有权限
    private void loadResourceDefine() {
        //应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
        List<Resource> allResource = resourceService.getAllResource();
        Set<String> resourceIds = allResource.stream().map(Resource::getResourceId).collect(Collectors.toSet());
        List<RoleResource> roleResources = roleResourceService.getRoleResourceByResourceIds(resourceIds);
        Map<String, List<RoleResource>> resourceIdListMap = roleResources.stream().collect(Collectors.groupingBy(RoleResource::getResourceId));


        for (Resource resource : allResource) {
            String url = resource.getUrl();

            List<RoleResource> roleResourceList = resourceIdListMap.get(resource.getResourceId());

            if (!CollectionUtil.isEmpty(roleResourceList)) {

                if (!resourceMap.containsKey(url) || resourceMap.get(url) == null) {
                    resourceMap.put(url, new LinkedList<>());
                }

                Collection<ConfigAttribute> configAttributes = resourceMap.get(url);

                for (RoleResource roleResource : roleResourceList) {
                    configAttributes.add(new SecurityConfig("ROLE_" + roleResource.getRoleId()));
                }
            }

        }

    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();


        Iterator<String> iterator = resourceMap.keySet().iterator();
        Collection<ConfigAttribute> configAttributes = Collections.emptyList();
        while (iterator.hasNext()) {
            String url = iterator.next();

            if (antPathMatcher.match(url, requestUrl)) {
                configAttributes = resourceMap.get(url);
                break;
            }
        }

        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> configAttributes = new HashSet<>();
        resourceMap.values().forEach(configAttributes::addAll);
        return configAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
