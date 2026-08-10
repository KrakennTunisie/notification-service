package com.kerp.notificationservice.application.ports.out;

import com.kerp.notificationservice.domain.records.NotificationDto;

public interface NotificationPushPort {
    void pushToUser(String userId, NotificationDto notification);
    void pushToRole(String role, NotificationDto notification);
}
