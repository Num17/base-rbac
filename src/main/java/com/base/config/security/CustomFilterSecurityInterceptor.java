package com.base.config.security;

//@Component
public class CustomFilterSecurityInterceptor
//        extends AbstractSecurityInterceptor implements Filter
{

//    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        invoke(new FilterInvocation(request, response, chain));
//    }
//
//    private void invoke(FilterInvocation fi) throws IOException,
//            ServletException {
//
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//
//        try {
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//        } finally {
//            super.afterInvocation(token, null);
//        }
//
//    }
//
//
//    @Override
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.filterInvocationSecurityMetadataSource;
//    }
//
//    @Autowired
//    public void setFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
//        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
//    }
}
