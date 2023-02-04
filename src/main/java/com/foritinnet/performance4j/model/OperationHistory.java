package com.foritinnet.performance4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = {"createdAt", "duration"})
public class OperationHistory {

    private long duration;
    private LocalDateTime createdAt;

    public OperationHistory(@NonNull OperationDetail operationDetail) {

        duration = operationDetail.getDuration();
        createdAt = operationDetail.getCreatedAt();
    }
}
