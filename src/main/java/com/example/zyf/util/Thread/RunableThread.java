/**
 * @projectName demo
 * @package com.example.hbtc.demo.util
 * @className com.example.hbtc.demo.util.Thread
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Thread
 *
 * @description 多线程 实现Runable接口
 * @author zyf
 * @date 2021/5/26 14:25
 * @version 1.0
 */
public class RunableThread implements Runnable {
    Object object = new Object();
    private volatile int num = 100;
    AtomicInteger atomicInteger = new AtomicInteger(100);

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (num < 1) {
                    System.out.println("票已经卖完");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "抢到票" + num);
            }
            num--;
//            atomicInteger.getAndDecrement();
//            try {
//                Thread.sleep(1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

}


class Thread2 implements Runnable {
    Object o = new Object();

    public static void main(String[] args) {
        new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "主线程main正在运行");
            }
        }.run();
        // main线程
        for (int i = 1; i <= 10; i++) {
            System.out.println("main>>>" + i);
        }
        // 黄牛抢票
        RunableThread runableThread = new RunableThread();
//        new Thread(runableThread, "黄牛1").start();
//        new Thread(runableThread, "黄牛2").start();
//        new Thread(runableThread, "黄牛3").start();
        //
        Thread2 thread2 = new Thread2();
        new Thread(thread2).start();
        new Thread(thread2).start();
        new Thread(thread2).start();

    }

    int i = 10;

    @Override
    public void run() {
        synchronized (o) {
            i++;
            System.out.println("数字：" + i);
            try {
                System.out.println(Thread.currentThread().getName() + "进入休眠");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "休眠结束");
            i++;
            System.out.println("数字：" + i);
        }
    }
}
