package com.matrix.common.utils;

/**
 * @author meng
 */
public class RspConstants {

    public static final String RES_CODE_NAME = "resCode";
    public static final String RES_INFO_NAME = "resInfo";
    public static final String RES_TYPE_NAME = "resType";
    public static final String RES_DATA_NAME = "data";


    // 全局错误返回代码，以300开头
    public static final Integer RES_CODE_ERROR = 300; // 一般错误
    public static final Integer RES_CODE_ERROR_PARAM = 301; // 参数错误
    public static final Integer RES_CODE_ERROR_MEMBER_ID = 302; // MemberId 解析错误
    public static final Integer RES_CODE_ERROR_WEIXIN = 302; // 微信接口信息错误
    public static final Integer RES_CODE_ERROR_UNBIND = 303; // 同程账户未绑定
    public static final Integer RES_CODE_ERROR_COMMENT = 304; // 点评错误
    public static final Integer RES_CODE_ERROR_COMMENT_NO_PRIVILEGE = 305; // 没有点评权限
    public static final Integer RES_CODE_ERROR_COMMENT_OVER = 306; // 已点评过
    public static final Integer RES_CODE_ERROR_MAILINFO_EXIST = 307; // 邮寄信息已经存在

    // 模块错误返回代码，以400开头

    // 成功代码
    public static final Integer RES_CODE_SUCCESS = 200; // 一般成功
    // 成功-查无结果代码
    public static final Integer RES_CODE_SUCCESS_NOINFO = 205; // 查询成功但查无记录

    // 返回类别
    public static final Integer RES_TYPE_SUCCESS = 0; // 成功
    public static final Integer RES_TYPE_ERROR = 1; // 已知错误
    public static final Integer RES_TYPE_EXCEPTION = 2; // 异常

    // 联名卡绑定错误代码
    public static final Integer REQUEST_FREQUENT = 400; // 请求频繁
    public static final Integer HAVE_BIND = 401; // 此会员已绑定
    public static final Integer NO_GRADE = 402; // 没有符合此卡号的卡等级
    public static final Integer ILLEGAL_CARD_NUMBER = 403; // 非法卡号
}
