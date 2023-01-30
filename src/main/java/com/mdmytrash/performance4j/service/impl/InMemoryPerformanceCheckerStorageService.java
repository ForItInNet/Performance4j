package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.collection.OperationDetailsStorage;
import com.mdmytrash.performance4j.service.PerformanceCheckerStorageService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class InMemoryPerformanceCheckerStorageService implements PerformanceCheckerStorageService {

    private final OperationDetailsStorage operationDetailIndexedStorage;

    public InMemoryPerformanceCheckerStorageService() {
        operationDetailIndexedStorage = new OperationDetailsStorage();
    }

    public InMemoryPerformanceCheckerStorageService(OperationDetailsStorage operationDetailsStorage) {
        this.operationDetailIndexedStorage = operationDetailsStorage;
    }

    @Override
    public String save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail) {

        return operationDetailIndexedStorage.put(name, group, operationDetail);
    }

    @Override
    public OperationDetail remove(@NonNull String name, @NonNull String group, @NonNull String operationDetailId) {

        return operationDetailIndexedStorage.remove(name, group, operationDetailId);
    }
}
