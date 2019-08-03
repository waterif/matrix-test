/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/06/17 20:55:47
 */
public class Human {
    static {
        System.out.println("1、父类静态语句块");
    }

    {
        System.out.println("3、父类实例语句块");
    }

    public Human() {
        System.out.println("4、父类构造方法");
    }
}
