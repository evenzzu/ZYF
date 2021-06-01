/**
 * @projectName ZYF
 * @package com.example.zyf.util.Thread
 * @className com.example.zyf.util.Thread.MyLock
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

import com.rabbitmq.tools.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * MyLock
 *
 * @description 锁机制
 * @author zyf
 * @date 2021/5/31 10:34
 * @version 1.0
 */
@Slf4j
public class MyLock {
    private static int MAX_PRODUCT = 10;
    private int produces = 0;
    private Lock lock = new ReentrantLock();

    @Scheduled(cron = "${sj}")
    @PostConstruct
    public synchronized void produce() {
        if (this.produces >= MAX_PRODUCT) {
            try {
                log.info("产品已满，暂停生产");
                wait();
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
            return;
        }
        this.produces++;
        log.info("生产者生产第" + this.produces + "个产品");
        notifyAll();
    }

    public synchronized void consumer() {
        if (this.produces < 0) {
            try {
                System.out.println("产品已空，暂停消费");
                wait();
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
            return;
        }
        this.produces--;
        log.info("消费者消费第" + this.produces + "个产品");
        notifyAll();
    }

    public void lock() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock();
        reentrantReadWriteLock.writeLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        if (reentrantLock.tryLock()) {
            try {
                // 处理事务
            } catch (Exception e) {
                log.info(e.getLocalizedMessage());
            } finally {
                reentrantLock.unlock();
            }
        } else {
            System.out.println("未获取锁");
        }

    }

    public static void main(String[] args) {
        MyLock myLock = new MyLock();
//        for (int i = 0; i < 10; i++) {
//            myLock.produce();
//        }

        new Thread(){
            @Override
            public void run(){
                myLock.insert(Thread.currentThread());
            }
        }.start();
        new Thread(){
            public void run(){
                myLock.insert(Thread.currentThread());
            }
        }.start();
        new Runnable() {
            @Override
            public void run() {
                System.out.println("main");
                myLock.insert(Thread.currentThread());
            }
        }.run();
    }
    public void insert(Thread thread){
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(lock.tryLock()){
            try{
                Thread.sleep(22);
                System.out.println(thread.getName()+"得到锁");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
