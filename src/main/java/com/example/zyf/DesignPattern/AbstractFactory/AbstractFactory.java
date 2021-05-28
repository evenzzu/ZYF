/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.AbstractFactory
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * AbstractFactory
 * @description
 * @author zyf
 * @date 2020/12/24 14:39
 * @version 1.0
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
    public void s(){}
}
