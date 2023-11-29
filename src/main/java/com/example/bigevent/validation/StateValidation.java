package com.example.bigevent.validation;

import com.example.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 泛型State要校验的注解，后面是校验数据的类型
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * @param s                          将来要校验的数据
     * @param constraintValidatorContext null
     * @return true成功，false失败
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        if (s == null) {
            return false;
        }
        return s.equals("已发布") || s.equals("草稿");
    }
}
