/**
 * @projectName ZYF
 * @package com.example.zyf.controller
 * @className com.example.zyf.controller.RedisController
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.example.zyf.DesignPattern.AbstractFactory.AbstractFactory;
import com.example.zyf.DesignPattern.AbstractFactory.Color;
import com.example.zyf.DesignPattern.AbstractFactory.Shape;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zyf.service.UserService;
import com.example.zyf.util.RedisUtil;

import com.example.zyf.model.User;

/**
 * RedisController
 * @description
 * @author zyf
 * @date 2020/12/21 20:30
 * @version 1.0
 */
@RestController
public class RedisController {
    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/getUser")
    public Object test(@Param("key") String key) {
        System.out.println("=============");
        Object o = redisUtil.get(key);
        if (o == null){
            List<User> users = userService.queryUser(key);
            redisUtil.set(key,users);
            redisUtil.expire(key,10);
            return users;
        } else {
            return o;
        }
    }

    /**
     *
     * RedisController
     * @description 获取验证码
     * @param name
     * @return java.lang.String
     * @date 2021/2/26 18:14
     * @author zyf
     * @version 1.0
     */
    @GetMapping("/yzm/{name}")
    public String yzm(@PathVariable("name") String name){
        int random =  (int)((Math.random())*1000000);
        System.out.println(random);
        redisUtil.hset("loginKey",name,random);
        redisUtil.expire("loginKey",60);
        return String.valueOf(random);
    }

    @GetMapping("login/{name}/{key}")
    public boolean login(@PathVariable String name,@PathVariable String key){
        Object loginKey = redisUtil.hget("loginKey", name);
        if (loginKey == null){
            long zyf = redisUtil.getExpire(name);
            System.out.println(zyf);
           return false;
        }
        long zyf = redisUtil.getExpire(name);
        System.out.println(zyf);
        boolean b =key.equals(loginKey.toString());
        return b;
    }
    @RequestMapping("/set")
    public boolean redisset() {
        User userEntity = new User();
        userEntity.setUsername("zyf");
        userEntity.setPassword("123123");
        String s = userEntity.toString();
        System.out.println(s);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");


        Map<String, Map<String,String>> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map1.put(UUID.randomUUID().toString(),"zyf");
        }
        for (int i = 0; i < 10000; i++) {
            map2.put(UUID.randomUUID().toString(),"zzu");
        }
        for (int i = 0; i < 10000; i++) {
            map3.put(UUID.randomUUID().toString(),"zzuzyf");
        }
        map.put("map1",map1);
        map.put("map2",map2);
        map.put("map3",map3);
        redisUtil.hset("myhash","zyf",map);
        return redisUtil.set("zyf", userEntity);
    }

    @RequestMapping("get")
    public Object redisget(@Param("key") String key) {
//        redisUtil.hset("zzz", "zyf", "zyf");
//        Object hget = redisUtil.hget("zzz", "zyf");
//        System.out.println(hget);
        Object o = redisUtil.get(key);
        System.out.println(o);
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
