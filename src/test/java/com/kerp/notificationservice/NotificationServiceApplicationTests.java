package com.kerp.notificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.cloud.config.enabled=false"
})
@ActiveProfiles("test")
class NotificationServiceApplicationTests {

    @MockBean
    private JwtDecoder jwtDecoder;

    @MockBean
    private JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
    }

}
