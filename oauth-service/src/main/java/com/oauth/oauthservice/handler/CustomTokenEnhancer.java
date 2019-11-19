package com.oauth.oauthservice.handler;

import com.oauth.oauthservice.security.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        final Map<String, Object> additionalInformation = new HashMap<>();
        SecurityUser user = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
        //把用户的主键userId放进去
        additionalInformation.put("userId", user.getId());
        additionalInformation.put("username", user.getUsername());
        additionalInformation.put("authorities", user.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

        additionalInformation.put("organization", authentication.getName() + randomAlphabetic(4));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }
}
