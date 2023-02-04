package com.foritinnet.performance4j.controller;

import com.foritinnet.performance4j.model.OperationHistory;
import com.foritinnet.performance4j.service.PerformanceCheckerHistoryStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Queue;

@RestController
@RequestMapping("/performance4j")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "performance4j.history", name = "enabled", matchIfMissing = false)
public class Performance4jController {

    private final PerformanceCheckerHistoryStorageService performanceCheckerHistoryStorageService;

    @GetMapping("/history/{group}")
    public Map<String, Queue<OperationHistory>> getByHistory(@PathVariable("group") String group) {

        return performanceCheckerHistoryStorageService.getByGroup(group);
    }
}
