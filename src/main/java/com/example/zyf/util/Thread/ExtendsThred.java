/**
 * @projectName demo
 * @package com.example.hbtc.demo.util
 * @className com.example.hbtc.demo.util.ExtendsThred
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

/**
 *
 * ExtendsThred
 *
 * @description
 * @author zyf
 * @date 2021/5/26 14:38
 * @version 1.0
 */
public class ExtendsThred extends Thread {
    private String name;
    ExtendsThred(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name+"正在运行");
    }
}

class T1 {

    public static void main(String[] args) {
        com.example.hbtc.demo.util.ExtendsThred extendsThred = new com.example.hbtc.demo.util.ExtendsThred("Thread");
        new Thread(extendsThred).start();
        extendsThred.start();
    }
}
