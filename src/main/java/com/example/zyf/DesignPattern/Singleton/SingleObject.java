/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.Singleton
 * @className com.example.zyf.DesignPattern.Singleton.SingleObject
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.Singleton;

import com.google.gson.internal.$Gson$Preconditions;

/**
 * SingleObject
 * @description
 * @author zyf
 * @date 2020/12/24 15:05
 * @version 1.0
 */
public class SingleObject {
    // 饿汉模式
    private static SingleObject instance = new SingleObject();
    private SingleObject(){}
    public static SingleObject getInstance(){
        return instance;
    }
    public void showMessage(){
        System.out.println("Hello Word! Singleton");
    }
}
class Test{
    // 懒汉模式
    private static Test instance;
    private Test(){};
    public static synchronized Test getInstance(){
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
            tes();
        }catch (Exception e) {
            System.out.println("外层");
        }
    }
    public static void tes(){
        try {
            System.out.println(1/0);
        }catch (Exception e) {
            System.out.println("1/0出错");
        }
    }
}
