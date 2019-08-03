package com.matrix.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParsePostParamUtil {

    public static String getParam(HttpServletRequest request) throws IOException {
        String line = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        //针对post请求，设置允许接收中文
        request.setCharacterEncoding("UTF-8");
        //获取请求内容
        br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
