package com.oauth.oauthservice.config;

import com.oauth.oauthservice.handler.AuthExceptionEntryPoint;
import com.oauth.oauthservice.handler.CustomAccessDeniedHandler;
import com.oauth.oauthservice.handler.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsUtils;

import java.util.Arrays;

/**
 * @author caiwei
 * @version V1.0
 * @ClassName: ResourceServerConfiguration
 * @Function:
 * @Date: 2019/11/15 12:09
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;


    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    CustomTokenEnhancer customTokenEnhancer;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(DEMO_RESOURCE_ID).stateless(true)
                .authenticationEntryPoint(authExceptionEntryPoint) // 外部定义的token错误进入的方法
                .accessDeniedHandler(accessDeniedHandler); // 没有权限的进入方法

        resources.tokenServices(tokenServices());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/**").and()
                //
//                .cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 配置order访问控制，必须认证后才可以访问
                .antMatchers("/demo/**").authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
