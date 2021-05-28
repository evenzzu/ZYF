/**
 * @projectName demo
 * @package com.example.hbtc.demo.util
 * @className com.example.hbtc.demo.util.MyThreadPool
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * MyThreadPool
 *
 * @description
 * @author zhangyifan
 * @date 2021/5/26 16:36
 * @version 1.0
 */
public class MyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        Executors.newCachedThreadPool();
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        // 创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);
        // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            int temp = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "正在执行" + temp);
                }
            });
            Thread.sleep(1);
        }

        for (int i = 0; i < 100; i++) {
            int temp = i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行" + temp);
                }
            }, 1, TimeUnit.SECONDS);
        }

    }
}
