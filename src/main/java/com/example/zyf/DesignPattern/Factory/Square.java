/**
 * @projectName ZYF
 * @package com.example.zyf.Factory
 * @className com.example.zyf.Factory.Square
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.Factory;

/**
 * Square
 * @description
 * @author zyf
 * @date 2020/12/24 14:04
 * @version 1.0
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("draw :: Square");
    }
}
