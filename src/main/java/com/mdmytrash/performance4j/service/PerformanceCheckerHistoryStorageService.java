package com.mdmytrash.performance4j.service;

import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.model.OperationHistory;
import lombok.NonNull;

import java.util.Map;
import java.util.Queue;

public interface PerformanceCheckerHistoryStorageService {

    void save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail);
    Map<String, Queue<OperationHistory>> getByGroup(@NonNull String group);
}
