package com.mdmytrash.performance4j.collection;

import com.mdmytrash.performance4j.model.OperationDetail;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OperationDetailsStorage extends OperationStorage<Map<String, OperationDetail>> {

    public String put(@NonNull String name, @NonNull String group, @NonNull OperationDetail operationDetail) {

        String id = UUID.randomUUID().toString();
        getOperationDetails(name, group).put(id, operationDetail);

        return id;
    }

    public OperationDetail get(@NonNull String name, @NonNull String group, @NonNull String performanceTimestampId) {

        return getOperationDetails(name, group).get(performanceTimestampId);
    }

    public OperationDetail remove(@NonNull String name, @NonNull String group, @NonNull String performanceTimestampId) {

        return getOperationDetails(name, group).remove(performanceTimestampId);
    }

    protected Map<String, OperationDetail> getOperationDetails(@NonNull String name, @NonNull String group) {

        Map<String, Map<String, OperationDetail>> performanceTimestampGroup = getByGroup(group);
        Map<String, OperationDetail> performanceTimestamps = performanceTimestampGroup.get(name);

        if(performanceTimestamps == null) {
            performanceTimestamps = new HashMap<>();
            performanceTimestampGroup.put(name, performanceTimestamps);
        }

        return performanceTimestamps;
    }
}
