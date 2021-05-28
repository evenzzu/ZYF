/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.Red
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * Red
 * @description
 * @author zyf
 * @date 2020/12/24 14:36
 * @version 1.0
 */
public class Red implements Color{
    @Override
    public void fill() {
        System.out.println("Color:Red");
    }
}
