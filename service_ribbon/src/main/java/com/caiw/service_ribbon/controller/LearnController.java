package com.caiw.service_ribbon.controller;

import com.caiw.service_ribbon.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LearnController
 * @Author caiwe
 * @CreateTime 2018/9/7  15:46
 **/
@RestController
public class LearnController {
    @Autowired
    LearnService learnService;

    @GetMapping(value = "/learn")
    public String learn(@RequestParam String name) {
        return learnService.testService( name );
    }
}
