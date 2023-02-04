package com.foritinnet.performance4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = {"createdAt"})
public class OperationDetail {
    private Long start;
    private Long stop;
    private LocalDateTime createdAt;

    public OperationDetail() {

        start = System.currentTimeMillis();
        createdAt = LocalDateTime.now();
    }

    public void setStop(Long stop) {
        this.stop = stop;
    }

    public long getDuration() {

        if(start == null || stop == null) {
            return -1;
        }

        return stop - start;
    }
}
