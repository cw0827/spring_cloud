package com.oauth.oauthservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessEventHandler.class);

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Authentication authentication = (Authentication) event.getSource();
        if (!authentication.getAuthorities().isEmpty()) {
            log.info("用户：{} 登录成功", authentication.getPrincipal());
        }

    }
}
