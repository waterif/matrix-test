package com.matrix.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 缓存注解，适用于查询方法
 * @author waterif
 * @date 2019/02/21 10:54:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {
    
    String cacheName() default "";

    String key();

    /**
     * 缓存默认时长：600 </br>
     * 单位：秒
     * 
     * @return
     */
    long seconds() default 600;
}
