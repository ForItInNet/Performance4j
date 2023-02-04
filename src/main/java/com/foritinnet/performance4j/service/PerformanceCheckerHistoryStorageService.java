package com.foritinnet.performance4j.service;

import com.foritinnet.performance4j.model.OperationHistory;
import com.foritinnet.performance4j.model.OperationDetail;
import lombok.NonNull;

import java.util.Map;
import java.util.Queue;

public interface PerformanceCheckerHistoryStorageService {

    void save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail);
    Map<String, Queue<OperationHistory>> getByGroup(@NonNull String group);
}
