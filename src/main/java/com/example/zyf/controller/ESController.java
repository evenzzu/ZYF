package com.example.zyf.controller;

import com.example.zyf.service.AsyncService;
import com.example.zyf.service.EsAppTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class ESController {
    @Resource
    private EsAppTest esAppTest;
    @Resource
    private AsyncService asyncService;
    @GetMapping("/esget")
    public void estest(){
        try {
            esAppTest.get("megacorp", "employee", 1L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test1")
    public String test(){
            asyncService.test();
            asyncService.hello();
            return "success";
    }
}
