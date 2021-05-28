/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.Green
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * Green
 * @description
 * @author zyf
 * @date 2020/12/24 14:35
 * @version 1.0
 */
public class Green implements Color{
    @Override
    public void fill() {
        System.out.println("Color:Green");
    }
}
