package com.matrix.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author matrix
 * @date 2018年1月14日 下午4:11:37
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
public class StringUtil
{
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    private static final int CHAR_NUMBER = 0x01;

    private static final int CHAR_UPPERCASE_LITTER = 0x02;

    private static final int CHAR_LOWERCASE_LITTER = 0x04;

    private static final int CHAR_SIGN = 0x08;

    private static final int CHAR_SPACE = 0x10;

    private static final int CHAR_CONTROL = 0x20;

    private static final Pattern P_TAB_ENTER = Pattern.compile( "\t|\r|\n" );

    private static final Pattern P_ENTER = Pattern.compile( "\r|\n" );

    public static String replaceBlank( String str )
    {
        String dest = "";
        if ( str != null )
        {
            Matcher m = P_TAB_ENTER.matcher( str.trim() );
            dest = m.replaceAll( "" );
        }
        return dest;
    }

    public static String replaceEnter( String str )
    {
        String dest = "";
        if ( str != null )
        {
            Matcher m = P_ENTER.matcher( str );
            dest = m.replaceAll( "" );
        }
        return dest;
    }

    // ---------------------------------------------------------------- the one
    /**
     * Compares string with at least one from the provided array. If at least
     * one equal string is found, returns its index. Otherwise, <code>-1</code>
     * is returned.
     */
    public static int equalsOne( String src, String[] dest )
    {
        for ( int i = 0; i < dest.length; i++ )
        {
            if ( src.equals( dest[i] ) )
            {
                return i;
            }
        }
        return -1;
    }

    public static String randomString( int siz, int types )
    {
        if ( siz <= 0 )
        {
            return null;
        }

        return randomString( siz, getChars( types ) );
    }

    public static String randomStringSpecialScope( int siz, String charScope )
    {
        if ( siz <= 0 )
        {
            return null;
        }

        if ( StringUtils.isEmpty( charScope ) )
        {
            return null;
        }

        return randomString( siz, charScope );
    }

    public static String randomString( int siz, String chars )
    {
        if ( siz <= 0 || null == chars || chars.isEmpty() )
        {
            return null;
        }

        int i, charsSize = chars.length();
        StringBuilder res = new StringBuilder();
        SecureRandom rand = new SecureRandom();
        rand.setSeed( System.currentTimeMillis() );

        for ( i = 0; i < siz; i++ )
        {
            res.append( chars.charAt( rand.nextInt( charsSize - 1 ) ) );
        }

        return res.toString();
    }

    public static String getChars( int types )
    {
        int i;
        StringBuilder res = new StringBuilder();

        if ( 0 != (types & CHAR_NUMBER) )
        {
            for ( i = ( int ) '0'; i <= ( int ) '9'; i++ )
            {
                res.append( ( char ) i );
            }
        }

        if ( 0 != (types & CHAR_UPPERCASE_LITTER) )
        {
            for ( i = ( int ) 'A'; i <= ( int ) 'Z'; i++ )
            {
                res.append( ( char ) i );
            }
        }

        if ( 0 != (types & CHAR_LOWERCASE_LITTER) )
        {
            for ( i = ( int ) 'a'; i <= ( int ) 'z'; i++ )
            {
                res.append( ( char ) i );
            }
        }

        if ( 0 != (types & CHAR_SIGN) )
        {
            for ( i = ( int ) '!'; i <= ( int ) '/'; i++ )
            {
                res.append( ( char ) i );
            }

            for ( i = ( int ) ':'; i <= ( int ) '@'; i++ )
            {
                res.append( ( char ) i );
            }

            for ( i = ( int ) '['; i <= ( int ) '`'; i++ )
            {
                res.append( ( char ) i );
            }

            for ( i = ( int ) '{'; i <= ( int ) '~'; i++ )
            {
                res.append( ( char ) i );
            }
        }

        if ( 0 != (types & CHAR_SPACE) )
        {
            res.append( ' ' );
        }

        if ( 0 != (types & CHAR_CONTROL) )
        {
            for ( i = 1; i < 32; i++ )
            {
                res.append( ( char ) i );
            }

            res.append( ( char ) 127 );
        }

        return res.toString();
    }
    
    public static String urlEncoder(String value) {

        if (StringUtils.isNotBlank(value)) {

            try {
                return URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("URLEncoder failed:{}", value, e);
            }
        }

        return value;
    }
    
    public static String urlDecoder(String value) {

        if (StringUtils.isNotBlank(value)) {

            try {
                return URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("URLDecoder failed:{}", value, e);
            }
        }

        return value;
    }

    /** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str){  
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);  
    }  

    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str){  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  

    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhoneLegal(String str){  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
    public static Map<String, String> urlParamParse(String urlParam) {

        if (StringUtils.isBlank(urlParam)) {
            return null;
        }

        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        // 每个键值为一组 www.2cto.com
        arrSplit = urlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static void main( String[] args )
    {
        /*String tmp = " 这是 一个\r\n 测     试 \r\n";
        System.out.println( tmp );
        System.out.println( "[" + replaceBlank( tmp ) + "]" );*/
//        System.out.println("手机验证结果是"+isPhoneLegal("13413640070"));
        System.out.println( JSON.toJSONString(urlParamParse("openid=oOCyauFbI4_jZJLfPpf8SpvTca5g&token=20_j4HSfywg3VRo1Qp-exbGDUqcs76l1IQCmv9qjGJWp5pyju-8-WbXqpZbgzLRuK84x499VBz77gjpVOpmofzkGj9XOcZxDoUsaqQNEpKkfLQ&refreshtoken=20_amOSSBBpYGlrPn8XwmFAPDQBQlDIDxonf63T5zKVYSndrVVwXMgk89dB4-4CfHCRXGC5EIPy8nVubBocLJaxjA&userid=48Jf+7XMFPj3B6xbO1YRng==&unionid=ohmdTt3ML_aolpDajCw1vzaiEgs8&wxtcinfo=S2rQcjjs24Gvx6HJqQtXMLpJL8gxMbD5RLCYqs4hKEeObQ6DsQCkxGjPyhDw5bJhUkHbG4RlsAO6ssXQfURO43RINlVlC%2fb3leWL1VnR9Rt4A1EnK8WMy15GJArQ4%2fHW") ));
    }
    
}
