/**
 * @projectName ZYF
 * @package com.example.zyf.controller
 * @className com.example.zyf.controller.TestController
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.controller;

import com.example.zyf.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * TestController
 *
 * @description
 * @author zyf
 * @date 2021/1/19 15:00
 * @version 1.0
 */
@RestController
public class TestController {
    @Resource
    private AsyncService asyncService;
    @PostMapping(value = "/client")
    public void testClient(){
        asyncService.someRestCall("zyf");
    }
    @GetMapping(value = "/panda1.jpg")
    public String test01(){
        return "aaa";}
}
