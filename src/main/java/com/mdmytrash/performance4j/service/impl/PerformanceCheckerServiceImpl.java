package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.configuration.Performance4jConfigurationProperties;
import com.mdmytrash.performance4j.exception.Performance4jException;
import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.service.PerformanceCheckerHistoryStorageService;
import com.mdmytrash.performance4j.service.PerformanceCheckerService;
import com.mdmytrash.performance4j.service.PerformanceCheckerStorageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceCheckerServiceImpl implements PerformanceCheckerService {

    private final Performance4jConfigurationProperties performance4jConfigurationProperties;
    private final PerformanceCheckerStorageService performanceCheckerStorageService;
    private final PerformanceCheckerHistoryStorageService performanceCheckerHistoryStorageService;

    @Override
    public String start(@NonNull String name, @NonNull String grout) {

        OperationDetail operationDetail = new OperationDetail();
        return performanceCheckerStorageService.save(name, grout, operationDetail);
    }

    @Override
    public OperationDetail stop(@NonNull String name, @NonNull String group, @NonNull String performanceTimestampId) {

        OperationDetail operationDetail = performanceCheckerStorageService.remove(name, group, performanceTimestampId);

        if(operationDetail == null) {
            throw new Performance4jException(Performance4jException.TIMESTAMP_NOT_FOUNT);
        }

        operationDetail.setStop(System.currentTimeMillis());

        if(performance4jConfigurationProperties.getHistory().isEnabled()) {
            performanceCheckerHistoryStorageService.save(name, group, operationDetail);
        }

        return operationDetail;
    }
}
