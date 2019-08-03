package com.matrix.config;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    /*@Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("*.html");
        registration.setName("authFilter");
//        registration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,*.htm,*.jsp,monitor.jsp");
        registration.setOrder(1);
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean interfaceAuthFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new InterfaceAuthFilter());
        registration.addUrlPatterns("/*");
        registration.setName("interfaceAuthFilter");
        registration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,*.htm,*.html,*.jsp,monitor.jsp,/paymentcardcouponpay/*");
        registration.setOrder(1);
        return registration;
    }*/

}
