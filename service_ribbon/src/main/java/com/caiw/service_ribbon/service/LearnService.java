package com.caiw.service_ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName LearnServcice
 * @Author caiwe
 * @CreateTime 2018/9/7  15:43
 **/
@Service
public class LearnService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "testError")
    public String testService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/test?name="+name,String.class);
    }

    public String testError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
