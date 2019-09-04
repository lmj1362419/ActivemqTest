package com.org.test;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author lmj
 * @Date 2019/9/3 10:40
 */
public class JavaTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("1223");
        set.add("asdasd");
        set.add("sdfsa");
        String a = set.toString();
        System.out.println(a);
    }
}
