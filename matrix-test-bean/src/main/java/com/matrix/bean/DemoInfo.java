/**
 * Copyright (C) 2019 Tongcheng Network Technology Co., Ltd. All Rights Reserved.
 */
package com.matrix.bean;

import javax.validation.constraints.NotNull;

/**
 * @Description: demo
 * @author waterif
 * @date 2019/07/29 17:19:55
 * 
 *       <ul>
 *       <b>JSR-303原生支持的限制有如下几种： </b></br>
 * 
 *       <li>@Null | 限制只能为null</li> </br>
 *       <li>@NotNull | 限制必须不为null</li></br>
 *       <li>@AssertFalse | 限制必须为false</li></br>
 *       <li>@AssertTrue | 限制必须为true</li></br>
 *       <li>@DecimalMax(value) | 限制必须为一个不大于指定值的数字</li></br>
 *       <li>@DecimalMin(value) | 限制必须为一个不小于指定值的数字</li></br>
 *       <li>@Digits(integer,fraction) | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction</li></br>
 *       <li>@Future | 限制必须是一个将来的日期</li></br>
 *       <li>@Max(value) | 限制必须为一个不大于指定值的数字</li></br>
 *       <li>@Min(value) | 限制必须为一个不小于指定值的数字</li></br>
 *       <li>@Past | 限制必须是一个过去的日期</li></br>
 *       <li>@Pattern(value) | 定的正则表达式</li></br>
 *       <li>@Size(max,min) | 限制字符长度必须在min到max之间</li></br>
 * 
 *       <b>除此之外，hibernate也还提供了其它的限制校验,在org.hibernate.validator.constraints包下 </b></br>
 *       <li>@NotBlank(message =) 验证字符串非null，且长度必须大于0</li></br>
 *       <li>@Email 被注释的元素必须是电子邮箱地址</li></br>
 *       <li>@Length(min=,max=) 被注释的字符串的大小必须在指定的范围内</li></br>
 *       <li>@NotEmpty 被注释的字符串的必须非空</li></br>
 *       <li>@Range(min=,max=,message=) 被注释的元素必须在合适的范围内单独使用hibernate validation校验bean对象</li></br>
 *       </ul>
 */
public class DemoInfo {

    @NotNull(message = "unionid不能为空")
    private String unionid;

    @NotNull(message = "openid不能为空")
    private String openid;

    @NotNull(message = "name不能为空")
    private String name;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name: the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
