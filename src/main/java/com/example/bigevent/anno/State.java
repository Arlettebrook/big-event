package com.example.bigevent.anno;

import com.example.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    // 校验失败的提示信息
    String message() default "state参数的值只能是已发布或者草稿！";
    // 指定分组：默认是default
    Class<?>[] groups() default {};
    // 注解State的附加信息
    Class<? extends Payload>[] payload() default {};
}
