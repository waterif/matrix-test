/**
 */
package com.matrix.common.test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/04/27 10:55:53
 */
public class TestLong {
    public static void main(String[] args) {
        Long a = 1L;
        Long b = 1L;

        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }

        Long c = 127L;
        Long d = 127L;

        if (c == d) {
            System.out.println("c == d");
        } else {
            System.out.println("c != d");
        }

        Long e = 128L;
        Long f = 128L;

        if (e == f) {
            System.out.println("e == f");
        } else {
            System.out.println("e != f");
        }
    }
}
