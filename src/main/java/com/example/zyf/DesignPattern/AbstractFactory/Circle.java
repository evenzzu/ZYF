/**
 * @projectName ZYF
 * @package com.example.zyf.Factory
 * @className com.example.zyf.Factory.Circle
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * Circle
 * @description
 * @author zyf
 * @date 2020/12/24 14:05
 * @version 1.0
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw");
    }
}
