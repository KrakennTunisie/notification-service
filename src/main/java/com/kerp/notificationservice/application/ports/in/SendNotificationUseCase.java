package com.kerp.notificationservice.application.ports.in;

import com.kerp.notificationservice.domain.records.NotificationEvent;

public interface SendNotificationUseCase {
    void processNotification(NotificationEvent event);
}
