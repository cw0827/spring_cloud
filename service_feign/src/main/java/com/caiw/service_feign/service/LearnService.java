package com.caiw.service_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName LearnService
 * @Author caiwe
 * @CreateTime 2018/9/7  16:10
 **/

@FeignClient(value = "service-hi")
public interface LearnService {

    @RequestMapping(value ="test",method = RequestMethod.GET)
    String testFromClientOne(@RequestParam(value = "name") String name);
}
