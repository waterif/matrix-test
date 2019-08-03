package com.matrix.common.base;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author matrix
 * @date 2018年1月14日 下午4:10:40
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
public class ResponseEntity<T> {

    private Boolean isSucceed;

    private String retCode;

    private String retMsg;

    private String requestId;

    private T content;

    public ResponseEntity() {
        this.isSucceed = true;
        this.retCode = ErrorCode.SUCCESS;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(boolean success) {
        super();
        this.isSucceed = success;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(boolean success, String retMsg) {
        super();
        this.isSucceed = success;
        this.retMsg = retMsg;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(String retCode) {
        super();
        this.isSucceed = true;
        this.retCode = retCode;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(String retCode, String retMsg) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(String retCode, String retMsg, T retObj) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.content = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(boolean success, String retMsg, T retObj) {
        super();
        this.isSucceed = success;
        this.retMsg = retMsg;
        this.content = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(boolean success, String retCode, String retMsg, T retObj) {
        super();
        this.isSucceed = success;
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.content = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(boolean success, T retObj) {
        super();
        this.isSucceed = success;
        this.content = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity(T retObj) {
        super();
        this.isSucceed = true;
        this.retCode = ErrorCode.SUCCESS;
        this.retMsg = "SUCCESS";
        this.content = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }


    /**
     * @return the content
     */
    public T getContent() {
        return content;
    }

    /**
     * @param content: the content to set
     */
    public void setContent(T content) {
        this.content = content;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    /**
     * @return the isSucceed
     */
    public Boolean getIsSucceed() {
        return isSucceed;
    }

    /**
     * @param isSucceed: the isSucceed to set
     */
    public void setIsSucceed(Boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    @Override
    public String toString() {
        return "ResponseEntity [retCode=" + retCode + ", retMsg=" + retMsg + ", requestId=" + requestId + ", retObj="
            + content + "]";
    }

}
