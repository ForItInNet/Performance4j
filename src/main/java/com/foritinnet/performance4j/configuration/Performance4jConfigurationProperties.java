package com.foritinnet.performance4j.configuration;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "performance4j")
public class Performance4jConfigurationProperties {

    private boolean enabled = true;

    private final History history = new History();

    @Setter
    @Getter
    public static class History {

        private boolean enabled = false;

        @Max(Integer.MAX_VALUE)
        @Min(1)
        private int size = 50;

        private final Datasource datasource = new Datasource();

        @Setter
        @Getter
        public static class Datasource {

            private String url;
            private String username;
            private String password;
        }
    }
}
