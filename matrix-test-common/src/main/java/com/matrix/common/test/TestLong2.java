/**
 */
package com.matrix.common.test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/04/27 10:55:53
 */
public class TestLong2 {
    public static void main(String[] args) {

        System.out.println(Long.valueOf("1") == Long.valueOf("1"));
        System.out.println(Long.valueOf("127") == Long.valueOf("127"));
        System.out.println(Long.valueOf("128") == Long.valueOf("128"));
        
        System.out.println("--------------------");
        
        System.out.println(Long.parseLong("1") == Long.parseLong("1"));
        System.out.println(Long.parseLong("127") == Long.parseLong("127"));
        System.out.println(Long.parseLong("128") == Long.parseLong("128"));

    }
}
