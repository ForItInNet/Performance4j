package com.mdmytrash.performance4j.annotetion;

import com.mdmytrash.performance4j.configuration.Performance4jConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Import(Performance4jConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePerformanceChecker {
}

