package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.annotetion.PerformanceChecker;
import com.mdmytrash.performance4j.exception.Performance4jException;
import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.service.PerformanceCheckerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Log4j2
@Service
@RequiredArgsConstructor
public class PerformanceCheckerProxyService {

    private final PerformanceCheckerService performanceCheckerService;

    public Object createProxy(@NonNull Object object, @NonNull String beanName) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(new PerformanceCheckerProxyInvocationHandler(object, beanName));

        return enhancer.create();
    }

    private class PerformanceCheckerProxyInvocationHandler implements InvocationHandler {

        private final Object supperObject;
        private final String beanName;
        private final PerformanceChecker classPerformanceCheckerAnnotation;
        private Logger logger;

        public PerformanceCheckerProxyInvocationHandler(Object supperObject, String beanName) {

            Class<?> supperClass = supperObject.getClass();
            this.classPerformanceCheckerAnnotation = supperClass.isAnnotationPresent(PerformanceChecker.class) ? supperClass.getAnnotation(PerformanceChecker.class) : null;

            if(classPerformanceCheckerAnnotation != null && !classPerformanceCheckerAnnotation.enabled()) {
                throw new Performance4jException(Performance4jException.CAN_NOT_CREATE_PROXY);
            }

            this.supperObject = supperObject;
            this.beanName = beanName;
            this.logger = LogManager.getLogger(beanName);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            PerformanceChecker methodPerformanceCheckerAnnotation = method.isAnnotationPresent(PerformanceChecker.class) ? method.getAnnotation(PerformanceChecker.class) : null;

            if(methodPerformanceCheckerAnnotation != null && methodPerformanceCheckerAnnotation.enabled()) {
                return invokeWithChecking(method, args, getName(method, methodPerformanceCheckerAnnotation), getGroup(methodPerformanceCheckerAnnotation));
            }
            else if(classPerformanceCheckerAnnotation != null && Modifier.isPublic(method.getModifiers())) {
                return invokeWithChecking(method, args, getName(method, null), getGroup(null));
            }

            return method.invoke(supperObject, args);
        }

        private Object invokeWithChecking(Method method, Object[] args, String name, String group) throws Throwable {

            String operationDetailsId = performanceCheckerService.start(name, group);
            Object returnedValue = method.invoke(supperObject, args);
            OperationDetail operationDetail = performanceCheckerService.stop(name, group, operationDetailsId);

            logger.info("Operation {}[{}] lasted {} milliseconds", () -> name, () -> group, operationDetail::getDuration);

            return returnedValue;
        }

        private String getName(@NonNull Method method, PerformanceChecker performanceChecker) {

            if(performanceChecker == null) {
                return method.getName();
            }

            String nameFromAnnotation = performanceChecker.name();

            return Strings.isBlank(nameFromAnnotation) ? method.getName() : nameFromAnnotation;
        }

        private String getGroup(PerformanceChecker performanceChecker) {

            if(performanceChecker == null) {
                return classPerformanceCheckerAnnotation != null && !Strings.isBlank(classPerformanceCheckerAnnotation.group()) ? classPerformanceCheckerAnnotation.group() : beanName;
            }

            String groupNameFromAnnotation = performanceChecker.group();

            return !Strings.isBlank(groupNameFromAnnotation) ? groupNameFromAnnotation : classPerformanceCheckerAnnotation != null && !Strings.isBlank(classPerformanceCheckerAnnotation.group()) ? classPerformanceCheckerAnnotation.group() : beanName;
        }
    }
}
