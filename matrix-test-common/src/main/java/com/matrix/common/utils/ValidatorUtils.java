
package com.matrix.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;

import com.matrix.common.base.BaseException;
import com.matrix.common.base.ErrorCode;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-15 10:50
 */
public class ValidatorUtils {
    private static Validator validator;

    static {

        ValidatorFactory validatorFactory =
            Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory();
        validator = validatorFactory.getValidator();

        // validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * 
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws BaseException 校验不通过，则报BaseException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws BaseException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint =
                (ConstraintViolation<Object>)constraintViolations.iterator().next();
            throw new BaseException(ErrorCode.PARAM_INVALID,constraint.getMessage());
        }
    }
}
