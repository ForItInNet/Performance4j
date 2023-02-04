package com.foritinnet.performance4j.service.impl;

import com.foritinnet.performance4j.collection.OperationDetailsStorage;
import com.foritinnet.performance4j.model.OperationDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class InMemoryPerformanceCheckerStorageServiceTest {

    private OperationDetailsStorage operationDetailsStorage;

    private InMemoryPerformanceCheckerStorageService inMemoryPerformanceCheckerStorageService;

    @BeforeEach
    void doBeforeEach() {

        operationDetailsStorage = Mockito.mock(OperationDetailsStorage.class);
        inMemoryPerformanceCheckerStorageService = new InMemoryPerformanceCheckerStorageService(operationDetailsStorage);
    }

    @Test
    void save() {

        OperationDetail operationDetail = new OperationDetail();
        inMemoryPerformanceCheckerStorageService.save("name", "group", operationDetail);

        Mockito.verify(operationDetailsStorage, Mockito.times(1)).put("name", "group", operationDetail);
    }

    @Test
    void remove() {

        inMemoryPerformanceCheckerStorageService.remove("name", "group", "id");

        Mockito.verify(operationDetailsStorage, Mockito.times(1)).remove("name", "group", "id");
    }
}