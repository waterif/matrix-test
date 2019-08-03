/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.classloader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/08/03 15:51:56
 */
public class ClassLoader {

    /**
     * <方法概述>
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * <方法概述>
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * <方法概述>
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * <方法概述>
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    @Test
    public void test() {
        System.out.println("test");
    }

}
