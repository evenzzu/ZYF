/**
 * @projectName demo
 * @package com.example.hbtc.demo.util
 * @className com.example.hbtc.demo.util.Thread
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

/**
 *
 * Thread
 *
 * @description 多线程
 * @author zyf
 * @date 2021/5/26 14:25
 * @version 1.0
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在运行");
    }

}

class Thread2 extends Thread {
    public static void main(String[] args) {
        new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在运行");
            }
        }.run();

        new Thread(new com.example.hbtc.demo.util.MyThread()).start();
    }

}
