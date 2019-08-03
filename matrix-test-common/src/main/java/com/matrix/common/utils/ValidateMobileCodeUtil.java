package com.matrix.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.matrix.common.config.VerifyCodeConfig;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateMobileCodeUtil {

    private final static Logger logger = LoggerFactory.getLogger(ValidateMobileCodeUtil.class);

    @Autowired
    VerifyCodeConfig verifyCodeConfig;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 发送验证码
     * code=0  发送失败
     * code=1  发送成功
     * code=-1 每日发送次数已超过限定次数
     *
     * @param mobile
     * @return
     */
    public Map<String, Object> sendValidateCode(String mobile) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("IsSuccess", false);
        String sendValidateCodeRequest = "201204261002{\"AccountID\":\"f97f1493-692e-483d-bea0-1937f269c42a\",\"MethodName\":\"ProduceValidateCode\"," +
                "\"MethodVersion\":1,\"Namespace\":\"TongCheng.SOA.Interface.TCMember.VaildCode\",\"ReqTime\":\"\\/Date(1505725360934+0800)\\/\"," +
                "\"Version\":\"201204261002\"}#tcsoa#TongCheng.SOA.Interface.TCMember.VaildCode.Entities.ProduceValidateCodeRequest#tcsoa#TongCheng.SOA.Interface.TCMember.VaildCode.Entities, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null#tcsoa#{" +
                "\"Act\":{\"AppKey\":\"" + verifyCodeConfig.getAppKey() + "\",\"AppSecret\":\"" + verifyCodeConfig.getAppSecret() + "\"},\"MemberSystem\":0,\"Mobile\":" + mobile + ",\"MyCodeType\":1,\"NeedVaildMobile\":false,\"PlatId\":" + verifyCodeConfig.getPlatId() + ",\"SysFunction\":" + verifyCodeConfig.getSysFunction() + ",\"UseMyCode\":true}";
        logger.info("sendValidateCodeRequest:{}", sendValidateCodeRequest);
        String sendValidateCodeResponse;
        try {
            sendValidateCodeResponse = restTemplate.postForObject(verifyCodeConfig.getAddress(), sendValidateCodeRequest, String.class);
        } catch (Throwable e) {
            logger.error("验证码接口不可用");
            return result;
        }

        if (StringUtils.isEmpty(sendValidateCodeResponse)) {
            return result;
        }
        logger.info("sendValidateCodeResponse:{}", sendValidateCodeResponse);

        String[] arr = sendValidateCodeResponse.split("#tcsoa#");
        JSONObject first = JSONObject.parseObject(arr[0]);
        JSONObject second = JSONObject.parseObject(arr[1]);

        String rspCode = first.getString("RspCode");
        if (StringUtils.equalsIgnoreCase("0000", rspCode)) {
            if (second.getBoolean("IsSuccess")) {
                result.put("code", 1);
                result.put("IsSuccess", true);
                return result;
            } else {
                result.put("code", "-1");
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * 校验验证码
     *
     * @param mobile
     * @param verificationCode
     * @return
     */
    public boolean verifyValidateCode(String mobile, String verificationCode) {
        String verifyValidateCodeRequest = "201204261002{\"AccountID\":\"f97f1493-692e-483d-bea0-1937f269c42a\",\"MethodName\":\"MatchValidateCode\",\"MethodVersion\":1," +
                "\"Namespace\":\"TongCheng.SOA.Interface.TCMember.VaildCode\",\"ReqTime\":\"\\/Date(1505725434996+0800)\\/\",\"Version\":\"201204261002\"}" +
                "#tcsoa#TongCheng.SOA.Interface.TCMember.VaildCode.Entities.MatchValidateCodeRequest#tcsoa#TongCheng.SOA.Interface.TCMember.VaildCode.Entities, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null#tcsoa#{" +
                "\"Act\":{\"AppKey\":\"" + verifyCodeConfig.getAppKey() + "\",\"AppSecret\":\"" + verifyCodeConfig.getAppSecret() + "\"},\"MemberId\":0,\"MemberSystem\":0,\"Mobile\":" + mobile + ",\"PlatId\":" + verifyCodeConfig.getPlatId() + ",\"SysFunction\":" + verifyCodeConfig.getSysFunction() + ",\"VerificationCode\":" + verificationCode + "}";
        logger.info("verifyValidateCodeRequest:{}", verifyValidateCodeRequest);
        String verifyValidateCodeResponse;
        try {
            verifyValidateCodeResponse = restTemplate.postForObject(verifyCodeConfig.getAddress(), verifyValidateCodeRequest, String.class);
        } catch (Throwable e) {
            logger.error("验证码接口不可用");
            return false;
        }

        if (StringUtils.isEmpty(verifyValidateCodeResponse)) {
            return false;
        }
        logger.info("verifyValidateCodeResponse:{}", verifyValidateCodeResponse);

        String[] arr = verifyValidateCodeResponse.split("#tcsoa#");
        JSONObject first = JSONObject.parseObject(arr[0]);
        JSONObject second = JSONObject.parseObject(arr[1]);

        String rspCode = first.getString("RspCode");
        if (StringUtils.equalsIgnoreCase("0000", rspCode)) {
            if (second.getBoolean("IsSuccess")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
