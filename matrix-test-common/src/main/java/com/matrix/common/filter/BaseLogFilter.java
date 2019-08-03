package com.matrix.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

import com.matrix.common.base.CommonThreadLocal;
import com.matrix.common.base.RequestHandler;
import com.matrix.common.contants.CommonConstant;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author matrix
 * @date 2018年1月14日 下午4:11:04
 *
 * @Copyright: 2018 www.matrix.com Inc. All rights reserved.
 */
@WebFilter(filterName = "baseLogFilter", urlPatterns = "/*",
    initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,*.html,*.htm") // 忽略资源
    })
@Order(0)
public class BaseLogFilter implements Filter {

    /** logger */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        MDC.put(CommonConstant.REQUEST_ID, RequestHandler.getRequestId());
        try {
            logger.debug("dofilter.");
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CommonConstant.REQUEST_ID);
            CommonThreadLocal.clear();
        }
    }

    @Override
    public void destroy() {}
}
