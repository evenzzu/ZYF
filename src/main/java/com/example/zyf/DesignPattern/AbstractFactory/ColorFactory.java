/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.ColorFactory
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

import com.example.zyf.model.User;

/**
 * ColorFactory
 * @description
 * @author zyf
 * @date 2020/12/24 14:37
 * @version 1.0
 */
public class ColorFactory extends AbstractFactory{
    @Override
    public Color getColor(String color) {
        switch (color){
            case "Red":
                return new Red();
            case "Green":
                return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;

    }
}
