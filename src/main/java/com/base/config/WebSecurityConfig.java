package com.base.config;

import com.base.config.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTH_SUFFIX = "AUTH_";
    private static final String REMEMBER_ME = "remember-me";
    private static final int TOKEN_VALIDITY_SECONDS = 300;

    private UserDetailsService userDetailsService;

    private LoginAuthenctiationFailureHandler loginAuthenctiationFailureHandler;
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    private UrlFilterInvocationSecurityMetadataSource securityMetadataSource;

    private DataSource dataSource;   //是在application.properites


    //加密工具,默认BCrypt算法加密,加密规则{加密算法名}-加密后密码
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    //记住我功能的token存取器配置
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    //忽略静态资源访问等
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**");
//        web.ignoring().antMatchers("/images/**");
//        web.ignoring().antMatchers("/js/**");
        //忽略登录界面
//        web.ignoring().antMatchers("/login");

        //注册地址不拦截
        //web.ignoring().antMatchers("/reg");
    }

    /**
     * 表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //自定义过滤器
        UrlFilterSecurityInterceptor filterSecurityInterceptor =
                new UrlFilterSecurityInterceptor(securityMetadataSource,
                        accessDecisionManager(), authenticationManagerBean());

        //加入自定定义的授权过滤器
        http.addFilterAt(filterSecurityInterceptor, FilterSecurityInterceptor.class);

        http
                .formLogin()
                .successHandler(loginAuthenticationSuccessHandler)
                .failureHandler(loginAuthenctiationFailureHandler)
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler) //权限不足处理
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter(REMEMBER_ME).userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()//必须经过认证以后才能访问
                .and()
                .csrf()
                .disable();
    }

    /**
     * 投票器,原来默认投标器基础之上新增特殊权限的角色投标者
     */
    private AbstractAccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());//角色投票器,默认前缀为ROLE_
        RoleVoter AuthVoter = new RoleVoter();
        AuthVoter.setRolePrefix(AUTH_SUFFIX);//特殊权限投票器,修改前缀为AUTH_
        decisionVoters.add(AuthVoter);
        return new AffirmativeBased(decisionVoters);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    public AuthenticationManager authenticationManagerBean() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setLoginAuthenctiationFailureHandler(LoginAuthenctiationFailureHandler loginAuthenctiationFailureHandler) {
        this.loginAuthenctiationFailureHandler = loginAuthenctiationFailureHandler;
    }

    @Autowired
    public void setLoginAuthenticationSuccessHandler(LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler) {
        this.loginAuthenticationSuccessHandler = loginAuthenticationSuccessHandler;
    }

    @Autowired
    public void setAuthenticationAccessDeniedHandler(AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler) {
        this.authenticationAccessDeniedHandler = authenticationAccessDeniedHandler;
    }

    @Autowired
    public void setSecurityMetadataSource(UrlFilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
