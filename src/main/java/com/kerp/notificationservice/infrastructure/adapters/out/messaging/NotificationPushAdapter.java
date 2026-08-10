package com.kerp.notificationservice.infrastructure.adapters.out.messaging;

import com.kerp.notificationservice.application.ports.out.NotificationPushPort;
import com.kerp.notificationservice.domain.records.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPushAdapter implements NotificationPushPort {
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void pushToUser(String userId, NotificationDto notification) {
        try {
            messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", notification);
            log.info("Notification sent");

        } catch (Exception e) {
            log.info("Exception ", e);
        }
    }

    @Override
    public void pushToRole(String role, NotificationDto notification) {
        messagingTemplate.convertAndSend("/topic/role." + role, notification);
    }
}
