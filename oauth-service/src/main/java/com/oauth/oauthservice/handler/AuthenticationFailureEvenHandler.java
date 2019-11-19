package com.oauth.oauthservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFailureEvenHandler.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        // 登录的authentication 对象
        AuthenticationException authenticationException = event.getException();
        // 登录的authenticationException 对象
        Authentication authentication = (Authentication) event.getSource();

        log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());


    }
}
