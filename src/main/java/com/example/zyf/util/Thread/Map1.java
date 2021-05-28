/**
 * @projectName demo
 * @package com.example.hbtc.demo.util
 * @className com.example.hbtc.demo.util.Map
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * Map
 *
 * @description
 * @author zyf
 * @date 2021/5/25 16:46
 * @version 1.0
 */
public class Map1 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","1");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
       while (iterator.hasNext()){
           Map.Entry<String, String> next = iterator.next();
           System.out.println(next.getKey());
           System.out.println(next.getValue());
       }

        Set<String> strings = map.keySet();
       strings.forEach(key->{
           System.out.println(key);
           System.out.println(map.get(key));
       });



    }
}
