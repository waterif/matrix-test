package com.matrix.common.base;

/**
 * @Description: 业务通用异常类
 * 
 * @author matrix
 * @date 2018年1月14日 下午12:47:44
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
public class BaseException extends RuntimeException {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -28861401581626723L;

    private String errorCode;

    private String errorMsg;

    /**
     * 构造方法
     */
    public BaseException() {
        this.errorCode = ErrorCode.SUCCESS;
    }

    /**
     * 构造方法 BaseException
     * 
     * @param errorMsg 异常描述
     */
    public BaseException(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = ErrorCode.ERROR_COMMON_FAILURE;
    }

    /**
     * @param errorMsg
     * @param cause
     */
    public BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
        this.errorCode = ErrorCode.ERROR_COMMON_FAILURE;
    }

    /**
     * @param errorCode
     * @param errorMsg
     * @roseuid 45F7E8850119
     */
    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public BaseException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * @return int
     * @roseuid 45F7E7CD006D
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * @param errorCode
     * @roseuid 45F7E7D8003E
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg: the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}