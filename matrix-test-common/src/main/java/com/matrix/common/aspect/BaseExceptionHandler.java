
package com.matrix.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.matrix.common.base.BaseException;
import com.matrix.common.base.ErrorCode;
import com.matrix.common.base.ResponseEntity;
import com.matrix.common.utils.ResponseUtil;

/**
 * @Description: controller层的异常处理器
 * @author Matrix
 * @date 2018/05/11 15:24:56
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理业务自定义异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleRRException(BaseException e) {

        logger.error(e.getMessage(), e);
        return ResponseUtil.returnFailed(e.getErrorCode(), e.getErrorMsg(), null);
    }

    /**
     * 数据库操作异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResponseUtil.returnFailed(ErrorCode.ERROR_COMMON_FAILURE, "数据库中已存在该记录", null);
    }

    // /**
    // * 权限不足异常
    // * @param e
    // * @return
    // */
    // @ExceptionHandler(AuthorizationException.class)
    // public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e) {
    // logger.error(e.getMessage(), e);
    // return new ResponseEntity<Object>(ErrorCode.ERROR_COMMON_FAILURE, "没有权限，请联系管理员授权");
    // }

    /**
     * 系统异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleException(MultipartException e) {
        logger.error(e.getMessage(), e);
        return ResponseUtil.returnFailed(ErrorCode.FILE_INVALID, ErrorCode.FILE_INVALID_DESC, null);
    }

    /**
     * 系统异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseUtil.returnFailed(ErrorCode.ERROR_COMMON_FAILURE, ErrorCode.ERROR_COMMON_FAILURE_DESC, null);
    }
}
