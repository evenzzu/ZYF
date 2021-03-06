/**
 * @projectName ZYF
 * @package com.example.zyf.service
 * @className com.example.zyf.service.TestService
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.service;

import com.example.zyf.model.User;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * TestService
 *
 * @description
 * @author zyf
 * @date 2021/1/20 18:40
 * @version 1.0
 */
public class TestService implements Runnable{

    @Test
    public void test02() {
        String s = "123456789";
        char c = s.charAt(8);
        int i = s.indexOf("8");
        String[] split = s.split("5");
        for (String s1 : split) {
            System.out.println(s1);
        }
        System.out.println(split.toString());
        System.out.println(i);
        System.out.println(c);
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("12");
        String s1 = list.get(2);
        System.out.println(s1);

    }

    @Test
    public void test2(){
        Hashtable hashtable = new Hashtable();
        hashtable.put("","1");
        Object o = hashtable.get("");
        System.out.println(o);
        HashMap map = new HashMap();
        map.put(null,"123");
        map.put(null,"1233");
        System.out.println(map.get(null));
        File file1 = new File("/banner.txt");
        File file2 = new File("/banner1.txt");
        boolean exists = file1.exists();
        System.out.println(exists);
        InputStream is = null;
        OutputStream os = null;
        try {
            os = new FileOutputStream(file2);
            try {
                Files.copy(file1.toPath(),os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        try {
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("?????????????????????" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executorService.submit(new TestService());
        }
        ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(size);
        /**
         * 1,executorService.scheduleAtFixedRate:?????????????????????????????????????????????????????????period??????????????????????????????????????????>period?????????????????????????????????
         * 2,executorService.scheduleWithFixedDelay?????????????????????????????????????????????????????????period????????????????????????
         */
        // ??????????????????????????????????????????????????????
        executorService1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("????????????"+Thread.currentThread().getName());
            }
        },5,2, TimeUnit.SECONDS);

        ExecutorService executorService2 = new ThreadPoolExecutor(1, 5, 5L,TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                System.out.println("Create thread" + thread.getName());
                return thread;
            }
        });
        for (int i = 0; i < size; i++) {
            executorService2.submit(new TestService());
        }

    }

    @Test
    public void t(){

        String S = "??????";
        String S1 = "??????";
        System.out.println(String.format("str1???%d | str2???%d",  S.hashCode(),S1.hashCode()));
        boolean b = S.equals(S1);

        System.out.println(b);
    }

}
