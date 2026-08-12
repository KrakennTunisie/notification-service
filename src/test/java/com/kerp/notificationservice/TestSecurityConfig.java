package com.kerp.notificationservice;


import com.kerp.notificationservice.infrastructure.config.JwtConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import static org.mockito.Mockito.mock;

// TestSecurityConfig.java
@TestConfiguration
@ComponentScan(
        basePackages = "com.kerp.notificationservice",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtConfig.class
        )
)
public class TestSecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        return mock(JwtDecoder.class);
    }
}