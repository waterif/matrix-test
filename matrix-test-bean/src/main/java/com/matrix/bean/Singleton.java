/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/06/17 21:11:05
 */
public class Singleton {
    private static Singleton singleton = new Singleton();

    public static int count1;

    public static int count2 = 0;

    private Singleton() {
        count1++;
        count2++;
    }

    public static Singleton getInstence() {
        return singleton;
    }
}
