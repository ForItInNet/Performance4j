package com.foritinnet.performance4j.service;

import com.foritinnet.performance4j.model.OperationDetail;
import lombok.NonNull;

public interface PerformanceCheckerStorageService {

    String save(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail);
    OperationDetail remove(@NonNull String name, @NonNull String group, @NonNull String operationDetailId);
}
