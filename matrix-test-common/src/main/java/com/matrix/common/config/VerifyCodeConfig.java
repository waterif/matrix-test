package com.matrix.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyCodeConfig {

    @Value("${validate.code.address}")
    private String address;
    @Value("${validate.code.appKey}")
    private String appKey;
    @Value("${validate.code.appSecret}")
    private String appSecret;
    @Value("${validate.code.platId}")
    private Integer platId;
    @Value("${validate.code.sysFunction}")
    private Integer sysFunction;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

    public Integer getSysFunction() {
        return sysFunction;
    }

    public void setSysFunction(Integer sysFunction) {
        this.sysFunction = sysFunction;
    }
}
