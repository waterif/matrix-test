package com.matrix.common.contants;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author matrix
 * @date 2018年1月14日 下午4:10:05
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
public class ErrorCodeConstant {
    /**
     * 成功
     */
    public static final String SUCCESS = "0";
    
    public static final String SUCCESS_DESC = "SUCCESS";

    /**
     * 未知错误
     */
    public static final String ERROR_UNKNOWN = "-1";

    /**
     * 通用错误
     */
    public static final String ERROR_COMMON_FAILURE = "1";
    public static final String ERROR_COMMON_FAILURE_DESC = "系统忙，请稍后重试";

    /**
     * 鉴权错误
     */
    public static final String AUTH_FAILED = "10000";
    public static final String AUTH_FAILED_DESC = "鉴权失败，请重新授权";

    /**
     * 用户名不存在
     */
    public static final String AUTH_FAILED_USERNAME_INVALID = "10001";

    /**
     * 参数非法
     */
    public static final String PARAM_INVALID = "20000";
    public static final String PARAM_INVALID_DESC = "参数非法";
    
    public static final String FILE_INVALID = "20001";
    public static final String FILE_INVALID_DESC = "上传的文件非法";

}
