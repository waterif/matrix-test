package com.matrix.common.test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/04/27 10:45:00
 */
public class TestIong {

    public static void main(String[] args) {
        long a = 1L;
        long b = 1L;

        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }

        long c = 127L;
        long d = 127L;

        if (c == d) {
            System.out.println("c == d");
        } else {
            System.out.println("c != d");
        }

        long e = 128L;
        long f = 128L;

        if (e == f) {
            System.out.println("e == f");
        } else {
            System.out.println("e != f");
        }
    }
}
