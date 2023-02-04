package com.foritinnet.performance4j.collection;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class OperationStorage<T> {

    private final Map<String, Map<String, T>> storage = new HashMap<>();

    public Map<String, Map<String, T>> getAll() {
        return storage;
    }

    public Map<String, T> getByGroup(@NonNull String group) {

        Map<String, T> operationGroup = storage.get(group);

        if(operationGroup == null) {
            operationGroup = new HashMap<>();
            storage.put(group, operationGroup);
        }

        return operationGroup;
    }

    public void clear(){
        storage.clear();
    }
}
