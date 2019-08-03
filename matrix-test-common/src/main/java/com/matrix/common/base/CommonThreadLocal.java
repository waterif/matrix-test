package com.matrix.common.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 本地线程变量
 * @author matrix
 * @date 2018年1月14日 上午11:51:58
 * 
 */
public class CommonThreadLocal {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static Map<String, Object> getThreadLocalContext() {
        Map<String, Object> context = threadLocal.get();

        if (context == null) {
            context = new HashMap<String, Object>();

            threadLocal.set(context);
        }

        return context;
    }

    public static Object getAttribute(String attributeName) {
        Map<String, Object> context = threadLocal.get();

        if (null != context) {
            return context.get(attributeName);
        }

        return null;
    }

    public static void setAttribute(String attributeName, Object value) {
        Map<String, Object> context = threadLocal.get();

        if (context == null) {
            context = new HashMap<String, Object>();
            threadLocal.set(context);
        }

        context.put(attributeName, value);
    }

    public static void clear() {
        threadLocal.remove();
    }
}
