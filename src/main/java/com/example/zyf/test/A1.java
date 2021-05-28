/**
 * @projectName ZYF
 * @package com.example.zyf.test
 * @className com.example.zyf.test.A1
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * A1
 *
 * @description
 * @author zyf
 * @date 2021/3/19 10:25
 * @version 1.0
 */
public class A1 implements Runnable {
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        if (reentrantLock.tryLock()) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "：获取锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"：数据处理中");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
                System.out.println(Thread.currentThread().getName()+"：数据处理完成");
            }
            reentrantLock.unlock();
        } else {
            System.out.println(Thread.currentThread().getName() + "：未获取锁");
        }
    }

    public static void main(String[] args) {
        A1 a1 = new A1();
        new Thread(a1, "Thread1").start();
        new Thread(a1, "Thread2").start();
        new Thread(a1, "Thread3").start();


    }
}