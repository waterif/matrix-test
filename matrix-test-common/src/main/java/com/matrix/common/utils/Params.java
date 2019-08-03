package com.matrix.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数类，可以保存参数，设置参数
 *
 * @author mkk41112
 * @since 2016-11-20
 */
public class Params {
    /**
     * 保存具体参数
     */
    private Map<String, String> params;

    /**
     * 保存参数条件，是否必须
     */
    private Map<String, Boolean> paramsCheck;

    public Params() {
        params = new HashMap<>();
        paramsCheck = new HashMap<>();
    }

    public Params addParamCheck(String paramName, boolean required) {
        paramsCheck.put(paramName, required);
        return this;
    }

    public Boolean getParamCheck(String paramName) throws NoSuchParamExceptionActivity {
        if (!paramsCheck.containsKey(paramName)) throw new NoSuchParamExceptionActivity(paramName);
        return paramsCheck.get(paramName);
    }

    public Map<String, Boolean> getParamsCheck() {
        return paramsCheck;
    }

    public Integer paramsCheckSize() {
        return paramsCheck.size();
    }


    public Params addParam(String paramName, String value) {
        params.put(paramName, value);
        return this;
    }

    public String getParam(String paramName) throws NoSuchParamExceptionActivity {
        if (paramsCheck.containsKey(paramName) && !params.containsKey(paramName))
            throw new NoSuchParamExceptionActivity(paramName);
        return params.get(paramName);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Integer paramsSize() {
        return params.size();
    }


    public class NoSuchParamExceptionActivity extends CommRuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoSuchParamExceptionActivity(String paramName) {
            super("没有该名称的参数[" + paramName + "]", RspConstants.RES_CODE_ERROR_PARAM);
        }
    }
}
