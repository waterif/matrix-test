package com.matrix.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.matrix.common.base.RequestHandler;
import com.matrix.common.contants.CommonConstant;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author waterif
 * @date 2019/02/20 18:05:19
 */
@Component
@Aspect
@Order(0)
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger("InterfaceLog");

    /*@Pointcut("execution(* com.ly.member.activity.service..*.*(..))")*/
    @Pointcut("@annotation(com.ly.member.common.annotation.LogAnnotation)")
    public void logPointCut() {

    }

    /*@Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("AOP Before Advice...");
        logger.info("logger:" + logger.getName());
        logger.info("logger:" + logger.getClass());
    }*/

    /**
     * 接口日志
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 解析参数名值对和描述
        Signature sig = joinPoint.getSignature();

        MethodSignature signature = null;

        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        signature = (MethodSignature)sig;

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();

        MDC.put("class_name", className);
        MDC.put("method_name", methodName);
        MDC.put(CommonConstant.REQUEST_ID, RequestHandler.getRequestId());

        Class[] parameterTypes = signature.getParameterTypes();

        if (null != parameterTypes) {

            boolean ignore = false;

            for (Class parameterType : parameterTypes) {
                if (parameterType.getSimpleName().equals("HttpServletRequest")
                    || parameterType.getSimpleName().equals("MultipartFile")
                    || parameterType.getSimpleName().equals("HttpServletResponse")) {
                    ignore = true;
                    break;
                }
            }

            if (!ignore) {
                logger.info("request args:{}", JSON.toJSONString(joinPoint.getArgs()));
            }
        }

        try {

            Object result = joinPoint.proceed();

            logger.info("response object:{}", JSONObject.toJSONString(result));

            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /*@After("logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("AOP After Advice...");
    }
    
    @AfterReturning(pointcut = "logPointCut()", returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        logger.info("AOP AfterReturning Advice:" + returnVal);
    }
    
    @AfterThrowing(pointcut = "logPointCut()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.info("AOP AfterThrowing Advice..." + error);
        logger.info("AfterThrowing...");
    }*/
}
