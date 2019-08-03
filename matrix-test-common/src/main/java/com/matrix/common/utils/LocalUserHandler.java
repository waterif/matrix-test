package com.matrix.common.utils;

import com.matrix.bean.UserInfo;
import com.matrix.common.base.CommonThreadLocal;
import com.matrix.common.contants.CommonConstant;

/**
 * @Description: 本地用户信息
 * @author Matrix
 * @date 2018/05/22 13:16:10
 */
public class LocalUserHandler {

    public static void setLocalUserInfo(UserInfo user) {
        CommonThreadLocal.setAttribute(CommonConstant.SESSION_USER_INFO, user);
    }

    public static UserInfo getLocalUserInfo() {
        return (UserInfo)CommonThreadLocal.getAttribute(CommonConstant.SESSION_USER_INFO);
    }

}
