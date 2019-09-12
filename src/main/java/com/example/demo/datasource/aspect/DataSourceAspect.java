package com.example.demo.datasource.aspect;

import com.example.demo.datasource.DataSourceName;
import com.example.demo.datasource.DynamicDataSource;
import com.example.demo.datasource.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @className: DataSourceAspect
 * @description:
 * @author: LiuLukuan
 * @date: 2019/9/12 11:52
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Pointcut("@annotation(com.example.demo.datasource.annotation.DataSource)")
    public void dataSourcePointCut(){}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceName.First);
            log.debug("set datasource is " + DataSourceName.First);
        } else {
            DynamicDataSource.setDataSource(ds.name());
            log.debug("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.debug("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
