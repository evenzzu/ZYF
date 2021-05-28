/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.AbstractFactoryDemo
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * AbstractFactoryDemo
 * @description
 * @author zyf
 * @date 2020/12/24 14:47
 * @version 1.0
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        FactoryProducter factoryProducter = new FactoryProducter();
        AbstractFactory colorFactory = factoryProducter.getFactory("ColorFactory");
        Color red = colorFactory.getColor("Red");
        red.fill();
        AbstractFactory shapeFactory = factoryProducter.getFactory("ShapeFactory");
        Shape circle = shapeFactory.getShape("Circle");
        circle.draw();
    }
}
