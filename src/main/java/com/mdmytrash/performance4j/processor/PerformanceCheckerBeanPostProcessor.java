package com.mdmytrash.performance4j.processor;

import com.mdmytrash.performance4j.annotetion.EnablePerformanceChecker;
import com.mdmytrash.performance4j.factory.PerformanceCheckerFactory;
import com.mdmytrash.performance4j.service.PerformanceCheckerService;

import javassist.util.proxy.ProxyFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class PerformanceCheckerBeanPostProcessor implements BeanPostProcessor {

    private final PerformanceCheckerFactory performanceCheckerFactory;
    private final PerformanceCheckerService performanceCheckerService;

    public PerformanceCheckerBeanPostProcessor(PerformanceCheckerFactory performanceCheckerFactory) {

        this.performanceCheckerFactory = performanceCheckerFactory;
        this.performanceCheckerService = performanceCheckerFactory.getPerformanceCheckerService();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> originalClass = bean.getClass();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setSuperclass(bean.getClass());

        for (Method method : bean.getClass().getMethods()) {

        }


        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    private boolean isPerformanceCheckerEnabled() {

        return true;
    }
}
