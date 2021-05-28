/**
 * @projectName ZYF
 * @package com.example.zyf.model
 * @className com.example.zyf.model.PropertyDemo
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.model;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
     * PropertyDemo
     * @description
     * @author zyf
     * @date 2021/1/6 11:35
     * @version 1.0
     */
    public class PropertyDemo {
        private String[] arrs;
        private List<String> list;
        private Map<String, String> map;
        private Properties properties;

        public String[] getArrs() {
            return arrs;
        }

        public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
        User user = (User) context.getBean("p");
        System.out.println(user.getId());
    }
}
