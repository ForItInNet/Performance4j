package com.foritinnet.performance4j.processor;

import com.foritinnet.performance4j.annotetion.PerformanceChecker;
import com.foritinnet.performance4j.service.impl.PerformanceCheckerProxyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class PerformanceCheckerBeanPostProcessor implements BeanPostProcessor {

    private final PerformanceCheckerProxyService performanceCheckerProxyService;

    @Lazy
    public PerformanceCheckerBeanPostProcessor(PerformanceCheckerProxyService performanceCheckerProxyService) {

        this.performanceCheckerProxyService = performanceCheckerProxyService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(hasEnabledPerformanceChecker(bean)) {
            return performanceCheckerProxyService.createProxy(bean, beanName);
        }

        return bean;
    }

    private boolean hasEnabledPerformanceChecker(Object bean) {

        Class<?> beanClass = bean.getClass();
        PerformanceChecker performanceCheckerAnnotation = beanClass.isAnnotationPresent(PerformanceChecker.class) ? beanClass.getAnnotation(PerformanceChecker.class) : null;

        if(performanceCheckerAnnotation != null && !performanceCheckerAnnotation.enabled()) {
            return false;
        }

        for(Method method: beanClass.getMethods()) {
            if(method.isAnnotationPresent(PerformanceChecker.class) && method.getAnnotation(PerformanceChecker.class).enabled()) {
                return true;
            }
        }

        return false;
    }
}
