package com.mdmytrash.performance4j.service.impl;

import com.mdmytrash.performance4j.configuration.Performance4jConfigurationProperties;
import com.mdmytrash.performance4j.exception.Performance4jException;
import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.service.PerformanceCheckerHistoryStorageService;
import com.mdmytrash.performance4j.service.PerformanceCheckerService;
import com.mdmytrash.performance4j.service.PerformanceCheckerStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

class PerformanceCheckerServiceImplTest {

    private Performance4jConfigurationProperties performance4jConfigurationPropertiesMock;
    private PerformanceCheckerStorageService performanceCheckerStorageServiceMock;
    private PerformanceCheckerHistoryStorageService performanceCheckerHistoryStorageServiceMock;

    private PerformanceCheckerService performanceCheckerService;

    @BeforeEach
    public void doBeforeEach() {

        performance4jConfigurationPropertiesMock = Mockito.mock(Performance4jConfigurationProperties.class);
        performanceCheckerStorageServiceMock = Mockito.mock(PerformanceCheckerStorageService.class);
        performanceCheckerHistoryStorageServiceMock = Mockito.mock(PerformanceCheckerHistoryStorageService.class);

        Mockito.when(performanceCheckerStorageServiceMock.save(Mockito.anyString(), Mockito.anyString(), Mockito.any(OperationDetail.class))).thenReturn(UUID.randomUUID().toString());

        Mockito.when(performance4jConfigurationPropertiesMock.getHistory()).thenReturn(Mockito.mock(Performance4jConfigurationProperties.History.class));

        performanceCheckerService = new PerformanceCheckerServiceImpl(performance4jConfigurationPropertiesMock, performanceCheckerStorageServiceMock, performanceCheckerHistoryStorageServiceMock);
    }

    @Test
    void start_test() {

        Assertions.assertNotNull(performanceCheckerService.start("name", "group"));

        Mockito.verify(performanceCheckerStorageServiceMock, Mockito.times(1)).save(Mockito.eq("name"), Mockito.eq("group"), Mockito.any());
    }

    @Test
    void stop_existWithoutHistoryTest() {

        Mockito.when(performanceCheckerStorageServiceMock.remove("name", "group", "token")).thenReturn(new OperationDetail());
        Mockito.when(performance4jConfigurationPropertiesMock.getHistory().isEnabled()).thenReturn(false);

        performanceCheckerService.stop("name", "group", "token");

        Mockito.verify(performanceCheckerStorageServiceMock, Mockito.times(1)).remove("name", "group", "token");
        Mockito.verify(performanceCheckerHistoryStorageServiceMock, Mockito.times(0)).save(Mockito.anyString(), Mockito.anyString(), Mockito.any(OperationDetail.class));
    }

    @Test
    void stop_existWithHistoryTest() {

        Mockito.when(performanceCheckerStorageServiceMock.remove("name", "group", "token")).thenReturn(new OperationDetail());
        Mockito.when(performance4jConfigurationPropertiesMock.getHistory().isEnabled()).thenReturn(true);

        performanceCheckerService.stop("name", "group", "token");

        Mockito.verify(performanceCheckerStorageServiceMock, Mockito.times(1)).remove("name", "group", "token");
        Mockito.verify(performanceCheckerHistoryStorageServiceMock, Mockito.times(1)).save(Mockito.anyString(), Mockito.anyString(), Mockito.any(OperationDetail.class));
    }

    @Test
    void stop_notExistTest() {

        Mockito.when(performanceCheckerStorageServiceMock.remove("name", "group", "token")).thenReturn(null);
        Mockito.when(performance4jConfigurationPropertiesMock.getHistory().isEnabled()).thenReturn(true);

        Performance4jException exception = Assertions.assertThrows(Performance4jException.class, () -> performanceCheckerService.stop("name", "group", "token"));
        Assertions.assertEquals(Performance4jException.TIMESTAMP_NOT_FOUNT, exception.getMessage());

        Mockito.verify(performanceCheckerStorageServiceMock, Mockito.times(1)).remove("name", "group", "token");
        Mockito.verify(performanceCheckerHistoryStorageServiceMock, Mockito.times(0)).save(Mockito.anyString(), Mockito.anyString(), Mockito.any(OperationDetail.class));
    }
}