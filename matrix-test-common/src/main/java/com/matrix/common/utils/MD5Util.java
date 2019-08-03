package com.matrix.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/06/02 16:45:35
 */
public class MD5Util {

    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 利用MD5进行加密
     * 
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if ( StringUtils.isBlank(str))
        {
            return "";
        }
        
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // 加密后的字符串
        String newstr = Base64.encodeBase64String(md5.digest(str.getBytes("utf-8")));

        return newstr;
    }

    public static String encoderUnsafe(String str) {
        try {
            return encoder(str);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    public static String MD5(String content, String key) {

        content += key;

        try {

            byte[] bytes = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder(bytes.length * 2);

            for (byte b : bytes) {

                if ((b & 0xFF) < 0x10) {

                    hex.append("0");

                }

                hex.append(Integer.toHexString(b & 0xFF));

            }

            return hex.toString().toUpperCase();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(encoderUnsafe("tongcheng666"));
        System.out.println(MD5("ohmdTtyVPnQdGwbr2Q2UxSWnPQDE" + 1545193533 + 66, "2ea5b85ff40d480f94882a0e02e6e336"));
    }
}
