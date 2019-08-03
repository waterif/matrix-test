package com.matrix.common.test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/04/27 10:45:00
 */
public class TestInt {

    public static void main(String[] args) {
        int a = 1;
        int b = 1;

        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }

        int c = 127;
        int d = 127;

        if (c == d) {
            System.out.println("c == d");
        } else {
            System.out.println("c != d");
        }

        int e = 128;
        int f = 128;

        if (e == f) {
            System.out.println("e == f");
        } else {
            System.out.println("e != f");
        }
    }
}
