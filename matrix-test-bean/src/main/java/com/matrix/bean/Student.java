/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/06/17 20:56:47
 */
public class Student extends Human {
    static {
        System.out.println("2、子类静态语句块");
    }

    {
        System.out.println("5、子类实例语句块");
    }

    public Student() {
        System.out.println("6、子类构造方法");
    }

    public static void test() {
        System.out.println("7、子类静态方法");
    }

    public static void main(String[] args) {
        new Student();
//        Student.test();
//        new Student();
        
        new Human();
    }
}
