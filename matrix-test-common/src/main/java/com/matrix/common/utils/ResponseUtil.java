package com.matrix.common.utils;

import com.matrix.common.base.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<T> returnSuccess(T content) {
        return new ResponseEntity<T>(true, content);
    }
    
    public static <T> ResponseEntity<T> returnSuccess(String retCode, String msg, T content) {
        return new ResponseEntity<T>(true, retCode, msg, content);
    }

    public static <T> ResponseEntity<T> returnFailed(String msg) {
        return new ResponseEntity<T>(false, msg);
    }

    public static <T> ResponseEntity<T> returnFailed(String msg, T content) {
        return new ResponseEntity<T>(false, msg, content);
    }

    public static <T> ResponseEntity<T> returnFailed(String retCode, String msg, T content) {
        return new ResponseEntity<T>(false, retCode, msg, content);
    }
}
