package com.example.demo.datasource.annotation;

import java.lang.annotation.*;

/**
 * @className: DataSource
 * @description: 多数据源注解
 * @author: LiuLukuan
 * @date: 2019/9/12 11:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
