package com.mock.annotation;


import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MockRule {
    //定义字段的顺序
    int order() default Integer.MIN_VALUE;

    //默认的字段长度大小
    int[] defaultSize() default {5, 6};


}
