/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.Singleton
 * @className com.example.zyf.DesignPattern.Singleton.SingletonPatternDemo
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.Singleton;

/**
 * SingletonPatternDemo
 * @description
 * @author zyf
 * @date 2020/12/24 15:07
 * @version 1.0
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {
        // TODO 编译时错误：构造函数 SingleObject() 是不可见的
        // SingleObject singleObject = new SingleObject();
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.showMessage();
    }
}
