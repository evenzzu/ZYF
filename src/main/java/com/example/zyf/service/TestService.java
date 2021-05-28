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
        System.out.println("固定大小线程池" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executorService.submit(new TestService());
        }
        ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(size);
        /**
         * 1,executorService.scheduleAtFixedRate:创建一个周期性任务，从上个任务开始，过period周期执行下一个（如果执行时间>period，则以执行时间为周期）
         * 2,executorService.scheduleWithFixedDelay：创建一个周期上午，从上个任务结束，过period周期执行下一个。
         */
        // 如果前边任务没有完成则调度也不会启动
        executorService1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("定时任务"+Thread.currentThread().getName());
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

        String S = "通话";
        String S1 = "重地";
        System.out.println(String.format("str1：%d | str2：%d",  S.hashCode(),S1.hashCode()));
        boolean b = S.equals(S1);

        System.out.println(b);
    }

}
