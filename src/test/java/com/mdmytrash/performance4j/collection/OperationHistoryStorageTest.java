package com.mdmytrash.performance4j.collection;

import com.mdmytrash.performance4j.model.OperationDetail;
import com.mdmytrash.performance4j.model.OperationHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationHistoryStorageTest {

    private final int HISTORY_SIZE = 3;
    private final OperationHistoryStorage operationHistoryStorage = new OperationHistoryStorage(HISTORY_SIZE);

    @AfterEach
    public void doAfterEach() {
        operationHistoryStorage.clear();
    }

    @Test
    void add() {

        OperationHistory operationHistory = new OperationHistory(new OperationDetail());
        operationHistoryStorage.add("name", "group", operationHistory);

        for(int i = 0; i < 3; ++i) {
            operationHistoryStorage.add("name", "group", new OperationHistory(new OperationDetail()));
        }

        Assertions.assertEquals(3, operationHistoryStorage.getOperationHistory("name", "group").size());
        Assertions.assertNotEquals(operationHistory, operationHistoryStorage.getOperationHistory("name", "group").element());
    }

    @Test
    void getOperationHistory() {

        for(int i = 0; i < 3; ++i) {
            operationHistoryStorage.add("name", "group", new OperationHistory(new OperationDetail()));
        }

        Assertions.assertEquals(3, operationHistoryStorage.getOperationHistory("name", "group").size());
    }
}