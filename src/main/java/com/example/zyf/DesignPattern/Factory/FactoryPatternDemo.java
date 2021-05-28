/**
 * @projectName ZYF
 * @package com.example.zyf.Factory
 * @className com.example.zyf.Factory.FactoryPatternDemo
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.Factory;

/**
 * FactoryPatternDemo
 * @description
 * @author zyf
 * @date 2020/12/24 14:11
 * @version 1.0
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape("Rectangle");
        circle.draw();
    }
}
