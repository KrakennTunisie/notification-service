package com.kerp.notificationservice.domain.records;

import java.time.Instant;
import java.util.UUID;

public record MailStatusEventDto(
        UUID eventId,
        String status,
        String errorMessage,
        Instant processedAt
) {
    @Override
    public String toString() {
        return "MailStatusEventDto{" +
                "eventId=" + eventId +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", processedAt=" + processedAt +
                '}';
    }
}
