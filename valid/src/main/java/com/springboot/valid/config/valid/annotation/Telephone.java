package com.springboot.valid.config.valid.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {

    String message() default "전화번호 형식이 올바르지 않습니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}
