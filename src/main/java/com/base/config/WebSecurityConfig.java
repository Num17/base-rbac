package com.base.config;

import com.base.config.security.LoginAuthenctiationFailureHandler;
import com.base.config.security.LoginAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REMEMBER_ME = "remember-me";
    private static final String LOGIN_PAGE = "/user/login";
    private static final String LOGIN_URL = "/dologin";

    private static final int TOKEN_VALIDITY_SECONDS = 300;


    private UserDetailsService userDetailsService;

    private LoginAuthenctiationFailureHandler loginAuthenctiationFailureHandler;
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;


    private DataSource dataSource;   //是在application.properites


    //加密工具,默认BCrypt算法加密
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

    /**
     * 表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
//                .loginPage(LOGIN_PAGE).loginProcessingUrl(LOGIN_URL)
                    .successHandler(loginAuthenticationSuccessHandler)
                    .failureHandler(loginAuthenctiationFailureHandler)
                    .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
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


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

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
}
