/**
 * @projectName ZYF
 * @package com.example.zyf.DesignPattern.AbstractFactory
 * @className com.example.zyf.DesignPattern.AbstractFactory.FactoryProducter
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DesignPattern.AbstractFactory;

/**
 * FactoryProducter
 * @description
 * @author zyf
 * @date 2020/12/24 14:50
 * @version 1.0
 */
public class FactoryProducter {
    public AbstractFactory getFactory(String factory){
        switch (factory){
            case "ColorFactory":
                return new ColorFactory();
            case "ShapeFactory":
                return new ShapeFactory();
        }
        return null;
    }
}
