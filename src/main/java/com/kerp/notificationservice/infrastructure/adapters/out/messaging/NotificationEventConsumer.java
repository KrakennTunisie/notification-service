package com.kerp.notificationservice.infrastructure.adapters.out.messaging;

import com.kerp.notificationservice.application.ports.in.SendNotificationUseCase;
import com.kerp.notificationservice.domain.records.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {
    private final SendNotificationUseCase notificationUseCase;

    @KafkaListener(topics = "kerp.notification.requested", groupId = "notification-service")
    public void handle(NotificationEvent event) {
        System.out.println("NotificationEvent: "+event);
        notificationUseCase.processNotification(event);
    }
}
