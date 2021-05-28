/**
 * @projectName ZYF
 * @package com.example.zyf.service
 * @className com.example.zyf.service.StudentService
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.zyf.mapper.UserMapper;
import com.example.zyf.model.User;

/**
 * StudentService
 * @description
 * @author zyf
 * @date 2020/12/21 20:04
 * @version 1.0
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
//
//    public static void main(String[] args) {
//        AtomicReference<User> userAtomicReference = new AtomicReference<>();
//        User user1 = new User("1", "2", "3");
//        userAtomicReference.set(user1);
//        System.out.println(userAtomicReference.get());
//        User user2 = new User("2", "3", "4");
//        boolean b = userAtomicReference.compareAndSet(null, user2);
//        System.out.println(userAtomicReference.get());
//        System.out.println(b);
//    }
//    @Test
//    public void test(){
//        AtomicReference<User> userAtomicReference = new AtomicReference<>();
//        User user1 = new User("3", "2", "3");
//        userAtomicReference.set(user1);
//        System.out.println(userAtomicReference.get());
//        User user2 = new User("4", "3", "4");
//        // 返回原来User 将新的User注入
//        User andSet = userAtomicReference.getAndSet(user2);
//        System.out.println(userAtomicReference.get());
//        System.out.println(andSet.toString());
//    }
//
//    @Test
//    public void test1(){
//        AtomicReference<User> atomicReference = new AtomicReference<>(new User("1","2","3"));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = atomicReference.get();
//                user.setPassword("123");
//                atomicReference.getAndSet(user);
//                System.out.println("Thread1-->"+atomicReference.get().toString());
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = atomicReference.get();
//                user.setPassword("123");
//                atomicReference.getAndSet(user);
//                System.out.println("Thread2-->"+atomicReference.get().toString());
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = atomicReference.get();
//                user.setPassword("1234");
//                atomicReference.getAndSet(user);
//                System.out.println("Thread3-->"+atomicReference.get().toString());
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = atomicReference.get();
//                user.setPassword("12345");
//                atomicReference.getAndSet(user);
//                System.out.println("Thread4-->"+atomicReference.get().toString());
//            }
//        }).start();
//    }
    public List<User> queryUser(String id){
        return userMapper.queryUser(id);
    }
    @Test
    public void test(){
        String s = "123";
        String s1 = "123";
        String z = new String(s);
        System.out.println(s==s1);
        System.out.println(s.equals(z));
        System.out.println(s==z);
    }
}
