/**
 * @projectName ZYF
 * @package com.example.zyf.test
 * @className com.example.zyf.test.Test
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.test;

import com.rabbitmq.tools.json.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Test
 *
 * @description
 * @author zyf
 * @date 2021/2/22 10:24
 * @version 1.0
 */
public class Test {
    public int a = 1;

    public static void main(String[] args) {
        String[] strings = new String[]{"a", "b", "c"};
        Integer[] nums = new Integer[]{1, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10};
        // 1，数组转List Arrays.asList(); 下边这种不定长 可add(); remove();
        List<Integer> list1 = new ArrayList<>(Arrays.asList(nums));
        list1.add(1); // 运行正确
        System.out.println(list1.toString());
        // 2，若List<Integer> integers = Arrays.asList(nums); 则数组定长 不可add remove；若有运行报错
        List<Integer> list2 = Arrays.asList(nums);
        // integers.remove(0);  // 运行报错
        // 3, 使用Collections.addAll();
        List<Integer> list3 = new ArrayList<>();
        Collections.addAll(list3, nums);
        // 4, 使用Stream中的Collector收集器
        List<Integer> list4 = Stream.of(nums).collect(Collectors.toList());
        System.out.println(list4.toString());

        System.out.println("==================");
        Stream<Integer> list = list1.stream().filter(s -> s > 5) // 6,6,7,8,9,10
                .distinct() // 去重 6,7,8,9,10
                .skip(2) // 去除前两个 8,9,10
                .limit(2);// 保留前两个 8,9
        list.forEach(System.out::println);
        System.out.println("==================");

        List<String> list5 = Arrays.asList("a,b,c", "1,2,3");
        // 将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list5.stream().map(s ->
                s.replaceAll(",", ""))
                .filter(s -> s.length() > 2);
        s1.forEach(System.out::println);

        System.out.println("==================");
        Stream<String> s2 = list5.stream().flatMap(s -> {
            // 将每一个元素转换为一个Stream
            String[] split = s.split(",");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        });
        s2.forEach(System.out::println);

        list1.stream().peek(s -> s.equals("1"));

        System.out.println("==============");
        String s = "123s";
        String intern = s.intern();
        System.out.println(intern);
        Test test = new Test();
        test.test1();

        System.out.println("===============");

        Integer i1 =200;
        Integer i2 =200;
        System.out.println("i1==i2: "+(i1==i2));
        Integer i3 =100;
        Integer i4 =100;
        System.out.println("i3==i4: "+(i3==i4));
    }

    public void test1() {
        S s = new S();
        s.s();
    }

    class S {
        public int a = 10;

        public void s() {
            System.out.println("-----------");
            int a = this.a;
            int b = Test.this.a;
            System.out.println(a);
            System.out.println(b);
        }
    }
}

class T2 {
    public static int a = 1;
    int b = 2;

    public void t() {
        int a = T2.this.b;
        class T4{
            int a = T2.this.b;
        }
    }
}
