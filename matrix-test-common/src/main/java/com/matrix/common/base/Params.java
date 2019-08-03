/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.common.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName Params
 * @Description check params from request
 * @Author mingke.tao
 * @Date 2019/4/1
 */
public abstract class Params {

    private Object request;

    private Boolean pass = true;

    private List<String> errorMessages = new ArrayList<>();

    public Params(Object request) {
        this.request = request;
    }

    /**
     * check parameter not null
     *
     * @param object
     * @param message
     * @return
     */
    public Params addCheckRequired(Object object, String message) throws Exception{
        if (Objects.equals(object, null)) {
            throw new Exception(message);
        }
        return this;
    }

    /**
     * check parameter which customize
     *
     * @return
     */
    protected abstract Params addCheck() throws Exception;

    public Params check() throws Exception {
        return addCheck();
    }

    public Object getRequest() {
        return request;
    }

    public Boolean getPass() {
        return pass;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
