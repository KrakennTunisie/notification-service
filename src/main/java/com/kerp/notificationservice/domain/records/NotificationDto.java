package com.kerp.notificationservice.domain.records;

import com.kerp.notificationservice.domain.enums.NotificationStatus;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record NotificationDto(
        UUID id,
        String eventId,
        String eventType,
        String channel,
        String userId,
        String title,
        String message,
        Map<String, Object> metadata,
        NotificationStatus status,
        Instant occurredAt,
        Instant createdAt
) {
}
