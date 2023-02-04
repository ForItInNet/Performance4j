package com.foritinnet.performance4j.collection;

import com.foritinnet.performance4j.model.OperationHistory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Queue;

@RequiredArgsConstructor
public class OperationHistoryStorage extends OperationStorage<Queue<OperationHistory>> {

    private final int HISTORY_SIZE;

    public void add(@NonNull String name, @NonNull String group, @NonNull OperationHistory operationHistory) {

        getOperationHistory(name, group).add(operationHistory);
    }
    protected Queue<OperationHistory> getOperationHistory(@NonNull String name, @NonNull String group) {

        Map<String, Queue<OperationHistory>> operationHistoryGroup = this.getByGroup(group);
        Queue<OperationHistory> operationHistories = operationHistoryGroup.get(name);

        if(operationHistories == null) {
            operationHistories = new LimitedQueue<>(HISTORY_SIZE);
            operationHistoryGroup.put(name, operationHistories);
        }

        return operationHistories;
    }
}
