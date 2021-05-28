/**
 * @projectName ZYF
 * @package com.example.zyf.Factory
 * @className com.example.zyf.Factory.Rectangle
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.Factory;

/**
 * Rectangle
 * @description
 * @author zyf
 * @date 2020/12/24 14:03
 * @version 1.0
 */
public class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("draw:Rectangle");
    }
}
