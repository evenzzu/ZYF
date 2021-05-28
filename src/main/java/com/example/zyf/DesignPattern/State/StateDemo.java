/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.State
 * @className com.example.zyf.DesignPattern.State.StateDemo
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.State;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.example.zyf.model.User;
import com.example.zyf.util.LRULocalCache;
import com.google.gson.Gson;

/**
 * StateDemo
 * @description
 * @author zyf
 * @date 2020/12/24 15:37
 * @version 1.0
 */
public class StateDemo {
    public static void main(String[] args) {
//        Context context = new Context();
//        context.setMessage("张三有事请假");
//        context.start(context);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String dateStr = "2020-09-09 12:12:12.222";
        String dateStr = "Wed Dec 16 15:16:09 CST 2020";
        String[] split = dateStr.split("-");
        if (split[0].length() == 4){
            try {
                Date parse = simpleDateFormat.parse(dateStr);
                System.out.println(parse);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try {
                Date date = new Date(dateStr);
                System.out.println(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test(){
        User user = new User();
        user.setPassword("123456");
        user.setUsername("123456");
        LRULocalCache.add("zyf",user);
        Object zyf = LRULocalCache.get("zyf");
        System.out.println(zyf);

//       JSON
        Object o = JSON.toJSON(user);
        System.out.println(o);
        User user1 = JSON.toJavaObject((JSON) o, User.class);
        System.out.println(user1);
//      Gson
        Gson gson = new Gson();
        Object o1 = gson.fromJson(String.valueOf(o),User.class);
        System.out.println(o1);
        String s = gson.toJson(user);
    }

    @Test
    public void test1(){
        Object zyf = LRULocalCache.get("zyf");
        System.out.println(zyf);
        String s= "123";
        System.out.println(s.substring(1,s.length()));

        if (1==1){
            System.out.println("-----------");
            System.out.println(1);
        }else if (1==1){
            System.out.println(2);
        }
    }

}
