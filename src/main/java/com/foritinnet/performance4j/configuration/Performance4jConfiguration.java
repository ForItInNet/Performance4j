package com.foritinnet.performance4j.configuration;

import com.foritinnet.performance4j.service.PerformanceCheckerHistoryStorageService;
import com.foritinnet.performance4j.service.impl.InMemoryPerformanceCheckerHistoryStorageService;
import com.foritinnet.performance4j.service.impl.PerformanceCheckerHistoryMongoDatabaseStorageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ComponentScan("com.mdmytrash.performance4j")
@RequiredArgsConstructor
public class Performance4jConfiguration {

    private final Performance4jConfigurationProperties performance4jConfigurationProperties;

    @Bean
    @ConditionalOnProperty(prefix = "performance4j.history", name = "enabled", matchIfMissing = false)
    public PerformanceCheckerHistoryStorageService getPerformanceCheckerHistoryStorageService() {

        String databaseUrl = performance4jConfigurationProperties.getHistory().getDatasource().getUrl();

        return databaseUrl != null && !databaseUrl.isBlank() ? new PerformanceCheckerHistoryMongoDatabaseStorageService(performance4jConfigurationProperties) : new InMemoryPerformanceCheckerHistoryStorageService(performance4jConfigurationProperties.getHistory().getSize());
    }
}
