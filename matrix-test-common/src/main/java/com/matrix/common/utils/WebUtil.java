package com.matrix.common.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author mkk41112
 */
public class WebUtil {

    private static Logger logger = Logger.getLogger(WebUtil.class);

    /**
     * 测试时用的jsonp调用
     *
     * @param jsonResult
     * @param callBack
     * @param response
     */
    public static void CallBackResult(Object jsonResult, String callBack, HttpServletResponse response) {
        try {
            OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/javascript; charset=utf-8");
            String data = callBack + "(" + (new ObjectMapper()).writeValueAsString(jsonResult) + ")";
            byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
            outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组\
            outputStream.close();
        } catch (Exception ex) {
            logger.error("查询IsBind接口失败，返回数据失败", ex);
        }
    }

    /**
     * 从 request 中获取请求参数<br>
     *
     * @param params  需要获取的参数设置
     * @param request
     * @return
     */
    public static Params getParams(Params params, HttpServletRequest request) throws CommRuntimeException {
        if (params != null && params.paramsCheckSize() > 0) {
            String json = request.getParameter("json");
            JSONArray jsonArray = JSONArray.parseArray(json);
            if (jsonArray == null || jsonArray.size() == 0) {
                throw new CommRuntimeException("请传入json格式的参数", RspConstants.RES_CODE_ERROR_PARAM);
            }

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            params = getParamsFromParamJson(params, jsonObject);
            return params;
        }
        return null;
    }

    /**
     * 从 request 中获取请求参数<br>
     * 如果参数中有 memberId，进行解码后保存
     *
     * @param paramsCheck 需要获取的参数设置
     * @param request
     * @return
     */
//    public static Params getParamsFromRequest(Params paramsCheck, HttpServletRequest request) {
//        Params params = getParams(paramsCheck, request);
//        // 如果有memberId，进行解码
//        if (params.getParams().containsKey("memberId")) {
//            String clearTxt = params.getParams().containsKey("clearTxt") ? params.getParam("clearTxt") : null;
//            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//            MemberHelper memberHelper = (MemberHelper) context.getBean("memberHelper");
//            String memberIdStr = memberHelper.varifyAndGetMemberIdStr(params.getParam("memberId"), clearTxt).toString();
//            params.getParams().put("memberId", memberIdStr);
//        }
//        return params;
//    }

    /**
     * 获取中间层调用的参数表，包含 functionCode
     *
     * @param request
     * @return
     */
//    public static JSONObject getParamJsonFromMiddleRequest(HttpServletRequest request) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            request.setCharacterEncoding("UTF-8");
//            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new RuntimeException(e.getMessage(), e);
//        }
//
//        String requestStr = sb.toString();
//        if (StringUtils.isEmpty(requestStr)) {
//            throw new CommRuntimeException("参数表为空", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//
//        JSONObject requestJson = JSONObject.parseObject(requestStr);
//
//        JSONObject protocolJson = requestJson.getJSONObject("protocol");
//        if (protocolJson.isEmpty() || StringUtils.isEmpty(protocolJson.getString("functionCode"))) {
//            throw new CommRuntimeException("请传入参数[protocol->functionCode]", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//
//        JSONObject paramJson = requestJson.getJSONObject("param");
//        if (paramJson == null) {
//            throw new CommRuntimeException("请传入参数[param]", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//        paramJson.put("functionCode", protocolJson.getString("functionCode"));
//
//        // 如果有memberId，进行解码
//        if (paramJson.containsKey("memberId")) {
//            paramJson.put("memberIdEnc", paramJson.getString("memberId"));
//            String clearTxt = paramJson.containsKey("clearTxt") ? paramJson.getString("clearTxt") : null;
//            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//            MemberHelper memberHelper = (MemberHelper) context.getBean("memberHelper");
//            String memberIdStr = memberHelper.varifyAndGetMemberIdStr(paramJson.getString("memberId"), clearTxt).toString();
//            paramJson.put("memberId", memberIdStr);
//        }
//        return paramJson;
//
//    }

    /**
     * 从ParamJson 中获取参数
     *
     * @param paramsCheck
     * @param paramJson
     * @return
     */
    public static Params getParamsFromParamJson(Params paramsCheck, JSONObject paramJson) {
        if (paramsCheck != null && paramsCheck.paramsCheckSize() > 0) {
            for (Map.Entry<String, Boolean> entry : paramsCheck.getParamsCheck().entrySet()) {
                String paramValue = paramJson.getString(entry.getKey());
                if (Strings.isNullOrEmpty(paramValue) && entry.getValue()) {
                    throw new CommRuntimeException("请传入合法的参数[" + entry.getKey() + "]", RspConstants.RES_CODE_ERROR_PARAM);
                }
                paramsCheck.addParam(entry.getKey(), paramValue);
            }
        }
        return paramsCheck;
    }

    /**
     * utf-8 的 paramsEntity
     *
     * @return
     */
    public static HttpEntity<String> paramsEntityUtf8(Object params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        String paramsJsonStr = JSONObject.toJSONString(params);
        HttpEntity<String> formEntity = new HttpEntity<>(paramsJsonStr, headers);
        return formEntity;
    }


    /**
     * 尤啸 支持GET请求
     *
     * @param request
     * @return
     */
//    public static JSONObject getGetParam(HttpServletRequest request) {
//        String jsonParam = request.getParameter("json");
//        jsonParam = jsonParam.substring(0, jsonParam.length() - 1);
//        jsonParam = jsonParam.substring(1, jsonParam.length());
//        String requestStr = jsonParam.toString();
//        if (StringUtils.isEmpty(requestStr)) {
//            throw new CommRuntimeException("参数表为空", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//
//        JSONObject requestJson = JSONObject.parseObject(requestStr);
//
//        JSONObject protocolJson = requestJson.getJSONObject("protocol");
//        if (protocolJson.isEmpty() || StringUtils.isEmpty(protocolJson.getString("functionCode"))) {
//            throw new CommRuntimeException("请传入参数[protocol->functionCode]", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//
//        JSONObject paramJson = requestJson.getJSONObject("param");
//        if (paramJson == null) {
//            throw new CommRuntimeException("请传入参数[param]", RspConstants.RES_CODE_ERROR_PARAM);
//        }
//        paramJson.put("functionCode", protocolJson.getString("functionCode"));
//
//        // 如果有memberId，进行解码
//        if (paramJson.containsKey("memberId")) {
//            paramJson.put("memberIdEnc", paramJson.getString("memberId"));
//            String clearTxt = paramJson.containsKey("clearTxt") ? paramJson.getString("clearTxt") : null;
//            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//            MemberHelper memberHelper = (MemberHelper) context.getBean("memberHelper");
//            String memberIdStr = memberHelper.varifyAndGetMemberIdStr(paramJson.getString("memberId"), clearTxt).toString();
//            paramJson.put("memberId", memberIdStr);
//        }
//        return paramJson;
//    }
}
