package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.collection.OperationHistoryStorage;
import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.model.OperationHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class InMemoryPerformanceCheckerHistoryStorageServiceTest {

    private OperationHistoryStorage operationHistoryStorage;
    private InMemoryPerformanceCheckerHistoryStorageService inMemoryPerformanceCheckerHistoryStorageService;

    @BeforeEach
    void doBeforeEach() {

        operationHistoryStorage = Mockito.mock(OperationHistoryStorage.class);
        inMemoryPerformanceCheckerHistoryStorageService = new InMemoryPerformanceCheckerHistoryStorageService(operationHistoryStorage);
    }

    @Test
    void save() {

        OperationDetail operationDetail = new OperationDetail();
        operationDetail.setStop(System.currentTimeMillis());

        inMemoryPerformanceCheckerHistoryStorageService.save("name", "group", operationDetail);

        Mockito.verify(operationHistoryStorage, Mockito.times(1)).add("name", "group", new OperationHistory(operationDetail));
    }

    @Test
    void getByGroup() {

        inMemoryPerformanceCheckerHistoryStorageService.getByGroup("group");

        Mockito.verify(operationHistoryStorage, Mockito.times(1)).getByGroup("group");
    }
}