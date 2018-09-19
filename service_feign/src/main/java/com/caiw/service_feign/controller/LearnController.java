package com.caiw.service_feign.controller;

import com.caiw.service_feign.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LearnController
 * @Author caiwe
 * @CreateTime 2018/9/7  16:13
 **/

@RestController
public class LearnController {

    @Autowired
    LearnService learnService;

    @GetMapping(value = "/learn")
    public String sayHi(@RequestParam String name) {
        return learnService.testFromClientOne(name);
    }
}
