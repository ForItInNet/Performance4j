package com.foritinnet.performance4j.service;

import com.foritinnet.performance4j.model.OperationDetail;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface PerformanceCheckerService {

    String start(@NonNull String name, @NonNull String grout);

    OperationDetail stop(@NonNull String name, @NonNull String group, @NonNull String performanceTimestampId);
}
