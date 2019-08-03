package com.matrix.common.test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/04/27 10:55:53
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;

        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }

        Integer c = 127;
        Integer d = 127;

        if (c == d) {
            System.out.println("c == d");
        } else {
            System.out.println("c != d");
        }

        Integer e = 128;
        Integer f = 128;

        if (e == f) {
            System.out.println("e == f");
        } else {
            System.out.println("e != f");
        }
    }
}
