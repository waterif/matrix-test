/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/03/27 16:41:24
 */
public class UserInfo {

    private String token;

    private String openid;

    private String unionid;

    private String nickname;

    private String city;

    private String province;

    private String country;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token: the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid: the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return the unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * @param unionid: the unionid to set
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname: the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city: the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province: the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country: the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the headimgurl
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * @param headimgurl: the headimgurl to set
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

}
