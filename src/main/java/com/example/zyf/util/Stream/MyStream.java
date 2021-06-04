/**
 * @projectName zyf
 * @package com.example.zyf.util.Stream
 * @className com.example.zyf.util.Stream.MyStream
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.Stream;

import com.example.zyf.model.User;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * MyStream
 *
 * @description Stream流
 * @author zyf
 * @date 2021/6/4 10:08
 * @version 1.0
 */
public class MyStream {
    static List<String> list = new ArrayList<>();
    static Integer[] integers = new Integer[]{1, 2, 3, 3, 5, 6, 9, 9};
    Stream<String> stream = list.stream();

    public static void stream1() {
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        boolean a1 = list.stream().filter(a -> !a.equals("a")).allMatch(a -> a == "1");
        System.out.println(a1);

        // 数组转为
        Stream<Integer> stream = Arrays.stream(integers);
        // filter 过滤
        Stream<Integer> distinct = stream.filter(a -> a > 1) // 2 3 3 5 6 9 9
                .distinct() // 去重 2 3 5 6 9
                .limit(4) // 获取前4个元素 2 3 5 6
                .skip(2); // 跳过n个元素 5 6
        distinct.forEach(x -> {
            System.out.println(x);
        });
        System.out.println("====================");
        Stream<Double> limit = Stream.generate(Math::random).limit(2);
        limit.forEach(System.out::println);
        System.out.println("=======================");
        // 迭代器 从0开始依次+2，一共8次
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2).limit(8).filter(x -> x > 8);
        iterate.forEach(System.out::println);

        // Pattern.splitAsStream() 方法，将字符串分隔成流
        Stream<String> stringStream = Pattern.compile(",").splitAsStream("a,b,c,d,e");
        stringStream.forEach(System.out::println);

        System.out.println("===============");
        list.stream().sorted().forEach(System.out::println);

    }

    public static void readStream() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Thunisoft\\Desktop\\小本本.txt"));
            //bufferedReader.read() 在运行时已经读取了一个空格
//            while (bufferedReader.read()!=-1){
//                String s1 = bufferedReader.readLine();
//                System.out.println(s1);
//            }
            System.out.println("==================");
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Thunisoft\\Desktop\\小本本.txt"));
            Stream<String> lines = bufferedReader.lines();
            lines.forEach(System.out::println);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void map() {
        User user1 = new User("1", "user1", "123");
        User user2 = new User("2", "user2", "123");
        List<User> users = Arrays.asList(user1, user2);
        // peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
        users.stream().peek(x -> {
            x.setPassword("321");
        }).forEach(System.out::println);
        System.out.println("================");
        List<String> list = Arrays.asList("a,b,c", "1,2,3", "q,w,e");
        // 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        Stream<String> stringStream = list.stream().map(x -> x.replaceAll(",", ""));
        stringStream.forEach(System.out::println);

        // 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<String> stringStream1 = list.stream().flatMap(s -> {
            String[] split = s.split(",");
            System.out.println(split);
            Stream<String> stream = Arrays.stream(split);
            System.out.println(stream);
            return stream;
        });
        stringStream1.forEach(System.out::print);

    }

    public static void match() {
        /**
         allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
         noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
         anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
         findFirst：返回流中第一个元素
         findAny：返回流中的任意元素
         count：返回流中元素的总个数
         max：返回流中元素最大值
         min：返回流中元素最小值
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        boolean b = list.stream().allMatch(x -> x > 1); // false
        boolean b1 = list.stream().noneMatch(x -> x > 5); // true
        boolean b2 = list.stream().anyMatch(x -> x > 2); // true
        Integer integer = list.stream().findAny().get(); // 任意值
        Integer integer1 = list.stream().findFirst().get(); // 1
        long count = list.stream().count(); // 5
        Integer integer2 = list.stream().max(Integer::compareTo).get(); // 5
        Integer integer3 = list.stream().min(Integer::compareTo).get(); // 1
        System.out.println(b + "," + b1 + "," + b2 + "," + integer + "," + integer1 + "," + count + "," + integer2 + "," + integer3);
    }

    public static void reduce() {

        // 经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);

        Integer v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(v1);  //310
        // T reduce(T identity, BinaryOperator<T> accumulator)：
        // 流程跟上面一样，只是第一次执行时，accumulator函数的第一个参数为identity，而第二个参数为流中的第一个元素。
        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2); // -300

        //  <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner)：
        // 在串行流(stream)中，该方法跟第二个方法一样，即第三个参数combiner不会起作用。在并行流(parallelStream)中,
        // 我们知道流被fork join出多个线程进行执行，此时每个线程的执行流程就跟第二个方法reduce(identity,accumulator)一样，
        // 而第三个参数combiner函数，则是将每个线程的执行结果当成一个新的流，然后使用第一个方法reduce(accumulator)流程进行规约。
        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v3); //197474048
    }

    public static void collect() {
        User user1 = new User("1", "user1", "123");
        User user2 = new User("2", "user2", "123");
        User user3 = new User("3", "user3", "123");
        List<User> users = Arrays.asList(user1, user2, user3);
        ConcurrentLinkedDeque<User> collect = users.stream().collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
        // 装成list
        List<String> collect1 = users.stream().map(User::getUsername).collect(Collectors.toList());
        // 装成set
        Set<String> collect2 = users.stream().map(User::getUsername).collect(Collectors.toSet());
        // 装成map
        Map<String, String> collect3 = users.stream().collect(Collectors.toMap(User::getUsername, User::getPassword));
        // 字符串分隔符连接
        String collect4 = users.stream().map(User::getUsername).collect(Collectors.joining(",","(",")"));

        System.out.println(collect4);
    }


    public static void main(String[] args) {
//        stream1();
//        readStream();
//        map();
//        match();
//        reduce();
        collect();
    }
}
