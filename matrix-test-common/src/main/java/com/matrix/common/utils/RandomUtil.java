package com.matrix.common.utils;

import java.security.SecureRandom;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/07/31 14:29:48
 */
public class RandomUtil {

    /**
     * 获取随机整数
     * 
     * @param bound 最大边界值
     * @return
     */
    public static int getRandomInt(int bound) {
        SecureRandom r = new SecureRandom();
        return r.nextInt(bound);
    }

    public static void main(String[] args) {
        System.out.println(getRandomInt(10) + 90);
    }
}
