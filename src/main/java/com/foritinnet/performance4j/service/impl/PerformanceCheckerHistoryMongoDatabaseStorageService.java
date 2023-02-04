package com.foritinnet.performance4j.service.impl;

import com.foritinnet.performance4j.configuration.Performance4jConfigurationProperties;
import com.foritinnet.performance4j.model.OperationHistory;
import com.foritinnet.performance4j.model.OperationDetail;
import com.foritinnet.performance4j.service.PerformanceCheckerHistoryStorageService;
import lombok.NonNull;

import java.util.Map;
import java.util.Queue;

public class PerformanceCheckerHistoryMongoDatabaseStorageService implements PerformanceCheckerHistoryStorageService {

    public PerformanceCheckerHistoryMongoDatabaseStorageService(@NonNull Performance4jConfigurationProperties performance4jConfigurationProperties) {

    }

    @Override
    public void save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail) {

        System.out.println("Saved - " + operationDetail);
    }

    @Override
    public Map<String, Queue<OperationHistory>> getByGroup(@NonNull String group) {

        return null;
    }
}
