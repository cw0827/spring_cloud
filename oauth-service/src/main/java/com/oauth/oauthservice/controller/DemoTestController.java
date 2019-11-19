package com.oauth.oauthservice.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.oauth.oauthservice.model.User;
import com.oauth.oauthservice.security.UserServiceDetail;
import com.oauth.oauthservice.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author caiwei
 * @version V1.0
 * @ClassName: DemoTestController
 * @Function:
 * @Date: 2019/11/15 12:00
 */
@RequestMapping("/demo")
@RestController
public class DemoTestController {

    private static final Logger log = LoggerFactory.getLogger(DemoTestController.class);

    @Autowired
    IUserService userService;

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: " + authentication.getAuthorities().toString());
        return "order id : " + id;
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            return userService.create(username, password);
        }
        return null;
    }

}
