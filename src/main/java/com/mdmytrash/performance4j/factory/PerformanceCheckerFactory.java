package com.mdmytrash.performance4j.factory;

import com.mdmytrash.performance4j.service.PerformanceCheckerService;
import com.mdmytrash.performance4j.service.impl.InMemoryPerformanceCheckerService;

import org.springframework.stereotype.Component;

@Component
public class PerformanceCheckerFactory {

    public PerformanceCheckerService getPerformanceCheckerService() {

        return new InMemoryPerformanceCheckerService();
    }
}
