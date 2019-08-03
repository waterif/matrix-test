package com.matrix.common.base;

import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.matrix.common.contants.CommonConstant;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author matrix
 * @date 2018年1月14日 下午4:10:16
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
public class RequestHandler {
    public static String getRequestId() {
        Map<String, Object> context = CommonThreadLocal.getThreadLocalContext();

        String requestId = null;

        if (context.containsKey(CommonConstant.REQUEST_ID)) {
            requestId = String.valueOf(context.get(CommonConstant.REQUEST_ID));
        }

        if (StringUtils.isEmpty(requestId)) {

            requestId = UUID.randomUUID().toString();
            context.put(CommonConstant.REQUEST_ID, requestId);
        }

        return requestId;

    }
}
