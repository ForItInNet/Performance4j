package com.mdmytrash.performance4j.collection;

import com.mdmytrash.performance4j.model.OperationDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationDetailsStorageTest {

    private final OperationDetailsStorage operationDetailsStorage = new OperationDetailsStorage();

    @AfterEach
    public void doAfterEach() {
        operationDetailsStorage.clear();
    }

    @Test
    void put() {

        operationDetailsStorage.put("name", "group", new OperationDetail());
        operationDetailsStorage.put("name", "group", new OperationDetail());

        Assertions.assertEquals(2, operationDetailsStorage.getByGroup("group").get("name").size());

        operationDetailsStorage.put("name1", "group", new OperationDetail());

        Assertions.assertEquals(2, operationDetailsStorage.getByGroup("group").size());

        operationDetailsStorage.put("name", "group1", new OperationDetail());

        Assertions.assertEquals(2, operationDetailsStorage.getAll().size());
    }

    @Test
    void get() {

        OperationDetail operationDetail = new OperationDetail();
        String operationDetailId = operationDetailsStorage.put("name", "group", operationDetail);

        Assertions.assertEquals(operationDetail, operationDetailsStorage.get("name", "group", operationDetailId));

        operationDetailId = operationDetailsStorage.put("name", "group", operationDetail);

        Assertions.assertEquals(operationDetail, operationDetailsStorage.get("name", "group", operationDetailId));

        operationDetailId = operationDetailsStorage.put("name1", "group", operationDetail);

        Assertions.assertEquals(operationDetail, operationDetailsStorage.get("name1", "group", operationDetailId));

        operationDetailId = operationDetailsStorage.put("name", "group1", operationDetail);

        Assertions.assertEquals(operationDetail, operationDetailsStorage.get("name", "group1", operationDetailId));
    }

    @Test
    void remove() {

        OperationDetail operationDetail = new OperationDetail();
        String operationDetailId = operationDetailsStorage.put("name", "group", operationDetail);

        operationDetailsStorage.remove("name", "group", operationDetailId);

        Assertions.assertNull(operationDetailsStorage.get("name", "group", operationDetailId));
    }

    @Test
    void getOperationDetails() {

        OperationDetail operationDetail = new OperationDetail();
        operationDetailsStorage.put("name", "group", operationDetail);
        operationDetailsStorage.put("name", "group", operationDetail);

        Assertions.assertEquals(2, operationDetailsStorage.getOperationDetails("name", "group").size());
    }
}