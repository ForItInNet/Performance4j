package com.foritinnet.performance4j.collection;

import com.foritinnet.performance4j.model.OperationDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationStorageTest {

    OperationStorage<OperationDetail> operationStorage = new OperationStorage<>();

    @AfterEach
    public void doAfterEach() {
        operationStorage.clear();
    }

    @Test
    void getByGroup() {

        Assertions.assertNotNull(operationStorage.getByGroup("group"));

        operationStorage.getByGroup("group").put("name", new OperationDetail());

        Assertions.assertNotNull(operationStorage.getByGroup("group").get("name"));
    }

    @Test
    void clear() {
        operationStorage.getByGroup("group").put("name", new OperationDetail());

        operationStorage.clear();

        Assertions.assertEquals(0, operationStorage.getAll().size());
    }
}