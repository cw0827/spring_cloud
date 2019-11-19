package com.oauth.oauthservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.oauth.oauthservice.mapper")
public class OauthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthServiceApplication.class, args);
    }

}
