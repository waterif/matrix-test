package com.matrix.common.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author matrix
 *
 */
public class SHAUtil {
    private static Logger logger = LoggerFactory.getLogger(SHAUtil.class);

    /**
     * MD5加密 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte)charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int)md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    /**
     * SHA1加密
     * 
     * @param str
     * @return
     */
    public static String string2Sha1(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(str.getBytes("UTF-8"));

        byte[] md = mdTemp.digest();
        int j = md.length;
        char buf[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }

    /**
     * 利用Apache的工具类实现SHA-256加密 所需jar包下載 http://pan.baidu.com/s/1nuKxYGh
     * 
     * @param str 加密后的报文
     * @return
     */
    public static String String2SHA256(String str) throws Exception {
        MessageDigest messageDigest;
        String encdeStr = "";
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        encdeStr = Hex.encodeHexString(hash);
        return encdeStr;
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * 
     * @param str 加密后的报文
     * @return
     */
    public static String String2SHA256StrJava(String str) throws Exception {
        MessageDigest messageDigest;
        String encodeStr = "";
        messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes("UTF-8"));
        encodeStr = byte2Hex(messageDigest.digest());
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * 
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String HmacSHA256Hex(String data, String key) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (byte item : array) {

            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));

        }
        return sb.toString().toUpperCase();
    }

    public static String HmacSHA256Hex2(String data, String key) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        return byte2Hex(array);
    }

    public static String HmacSHA256Base64(String data, String key) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(array);
    }
    
    public static String HmacSHA1Base64(String data, String key) throws Exception {
        
        Mac sha256_HMAC = Mac.getInstance("HmacSHA1");
        
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
        
        sha256_HMAC.init(secret_key);
        
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        
        return Base64.getEncoder().encodeToString(array);
    }

    public static void main(String args[]) {

        try {
//            System.out.println(SHAUtil.string2MD5("test"));
//            System.out.println(SHAUtil.string2Sha1("test"));
//            System.out.println(SHAUtil.String2SHA256("test"));
//            System.out.println(SHAUtil.String2SHA256StrJava("test"));
//            System.out.println(SHAUtil.HmacSHA256Hex("test", "tst"));
//            System.out.println(SHAUtil.HmacSHA256Hex2("test", "tst"));
//            System.out.println(SHAUtil.HmacSHA256Base64("test", "tst"));
            System.out.println("------活动防刷接口------");
            String tianyu = SHAUtil.HmacSHA1Base64("GETcsec.api.qcloud.com/v2/index.php?"
                + "Action=ActivityAntiRush"
                + "&Nonce=1541145964"
                + "&Region=gz"
                + "&SecretId=AKIDnV3PabxngPzObcP2WpwteDcqtkODoRpC"
//                + "&SignatureMethod=HmacSHA256"
                + "&Timestamp=1541145964"
                + "&accountType=2"
                + "&postTime=11254"
//                + "&appId=wxe0d8900d58d0206c"
                + "&uid=o498X0XgFdMv6DeQE59hOE71TUFs"
                + "&userIp=117.136.79.23"
                , "LTOXaTmxwjJhtsjQ5nPq57xd9TgpEXkn");
            System.out.println(tianyu);
            System.out.println(URLEncoder.encode(tianyu, "UTF-8"));
            System.out.println("------活动防刷接口------");
//            System.out.println(URLEncoder.encode(SHAUtil.HmacSHA1Base64("GETcsec.api.qcloud.com/v2/index.php?Action=ActivityAntiRush&Nonce=1998784305&Region=gz&SecretId=AKIDnV3PabxngPzObcP2WpwteDcqtkODoRpC&Timestamp=1541129473&accountType=2&postTime=11254&uid=o498X0XgFdMv6DeQE59hOE71TUFs&userIp=117.136.79.23", "LTOXaTmxwjJhtsjQ5nPq57xd9TgpEXkn"), "UTF-8"));
            System.out.println("------注册保护------");
//            String tianyu2 = SHAUtil.HmacSHA256Base64("GETcsec.api.qcloud.com/v2/index.php?"
//                + "accountType=1"
//                + "&Action=RegisterProtection"
                //+ "&appId=wxe0d8900d58d0206c"
//                + "&Nonce=581541069259"
//                + "&postTime=11254"
//                + "&registerIp=117.136.79.23"
//                + "&registerTime=1541069159"
                //+ "&Region=ap-guangzhou"
//                + "&SecretId=AKIDnV3PabxngPzObcP2WpwteDcqtkODoRpC"
//                + "&SignatureMethod=HmacSHA256"
//                + "&Timestamp=1541069159"
//                + "&uid=D692D87319F2098C3877C3904B304706"
//                + "&userIp=117.136.79.23"
//                , "LTOXaTmxwjJhtsjQ5nPq57xd9TgpEXkn");
//            System.out.println(tianyu2);
//            System.out.println(URLEncoder.encode(tianyu2, "UTF-8"));
//            System.out.println("-----注册保护-------");
//            System.out.println(SHAUtil.HmacSHA256Base64("GETcvm.api.qcloud.com/v2/index.php?"
//                + "Action=DescribeInstances"
//                + "&InstanceIds.0=ins-09dx96dg"
//                + "&Nonce=581541069159"
//                + "&Region=ap-guangzhou"
//                + "&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA"
//                + "&SignatureMethod=HmacSHA256"
//                + "&Timestamp=1541069159"
//                , "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
    }

}
