package com.mdmytrash.performance4j.annotetion;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ComponentScan("com.mdmytrash.performance4j")
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePerformanceChecker {
}
