package com.matrix.common.utils;



/**
 * 自定义的可以处理的运行时异常<br>
 * 日志中打印Info
 *
 * @author mkk41112
 * @since 2016-9-27
 */
public class CommRuntimeException extends RuntimeException {
    private Integer code;
    private Throwable ex;

    public CommRuntimeException(String message) {
        super(message);
        this.code = RspConstants.RES_CODE_ERROR;
    }

    public CommRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CommRuntimeException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.ex = cause;
    }

    public CommRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = RspConstants.RES_CODE_ERROR;
        this.ex = cause;
    }

    public CommRuntimeException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = RspConstants.RES_CODE_ERROR;
        this.ex = cause;
    }

    public Integer getCode() {
        return code;
    }

    public Throwable getEx() {
        return ex;
    }
}
