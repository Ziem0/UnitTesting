package com.ziemo.fileReader;

import lombok.Data;

import java.util.*;
import java.util.function.Predicate;

@Data
public class Ex {

    private int age;
    private String name;

    private static Queue<Ex> list = new PriorityQueue<Ex>(Comparator.comparing((Ex e) -> e.age).thenComparing((Ex e) -> e.age).reversed());

    public Ex(int age, String name) {
        this.age = age;
        this.name = name;
        list.offer(this);
    }

    boolean check(int i) {
        return i > 1;
    }


    public static void main(String[] args) {
        Ex e1 = new Ex(1, "a");
        Ex e2 = new Ex(1, "a");
        list.forEach(System.out::println);
        Predicate<Integer> pre = e1::check;
        System.out.println(pre.test(5));

    }


}
