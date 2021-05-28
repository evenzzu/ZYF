/**
 * @projectName ZYF
 * @package com.example.zyf.Factory
 * @className com.example.zyf.Factory.ShapeFactory
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * ShapeFactory
 * @description
 * @author zyf
 * @date 2020/12/24 14:06
 * @version 1.0
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        switch (shape) {
            case "Circle":
                return new Circle();
            case "Rectangle":
                return new Rectangle();
            case "Square":
                return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
