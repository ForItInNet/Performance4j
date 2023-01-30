package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.collection.OperationHistoryStorage;
import com.mdmytrash.performance4j.model.OperationHistory;
import com.mdmytrash.performance4j.service.PerformanceCheckerHistoryStorageService;
import lombok.NonNull;

import java.util.Map;
import java.util.Queue;

public class InMemoryPerformanceCheckerHistoryStorageService implements PerformanceCheckerHistoryStorageService {

    private final OperationHistoryStorage operationDetailHistoryStorage;

    public InMemoryPerformanceCheckerHistoryStorageService(int HISTORY_SIZE) {

        this.operationDetailHistoryStorage = new OperationHistoryStorage(HISTORY_SIZE);
    }

    public InMemoryPerformanceCheckerHistoryStorageService(OperationHistoryStorage operationHistoryStorage) {

        this.operationDetailHistoryStorage = operationHistoryStorage;
    }

    @Override
    public void save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail) {

        operationDetailHistoryStorage.add(name, group, new OperationHistory(operationDetail));
    }

    @Override
    public Map<String, Queue<OperationHistory>> getByGroup(@NonNull String group) {

        return operationDetailHistoryStorage.getByGroup(group);
    }
}
