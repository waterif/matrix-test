package com.matrix.common.test;

import com.matrix.bean.Singleton;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/06/17 21:14:32
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstence();
        System.out.println(s.count1);
        System.out.println(s.count2);
    }
}
