package com.kerp.notificationservice.domain.records;


import java.time.Instant;
import java.util.List;
import java.util.Map;


public record NotificationEvent(
        String eventId,          // UUID, for idempotency downstream
        String eventType,        // "INVOICE_CREATED", "PAYMENT_STATUS_UPDATED", etc.
        String channel,          // "INVOICE", "PAYMENT", "PURCHASE_ORDER" — used for frontend routing/filtering
        List<String> targetUserIds,   // specific users
        List<String> targetRoles,     // e.g. "ADMIN", "ACCOUNTANT" — role-wide broadcast
        String title,
        String message,
        Map<String, Object> metadata, // invoiceId, amount, status, etc. — whatever the frontend needs to deep-link
        Instant occurredAt
) {

    @Override
    public String toString() {
        return "NotificationEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", channel='" + channel + '\'' +
                ", targetUserIds=" + targetUserIds +
                ", targetRoles=" + targetRoles +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", metadata=" + metadata +
                ", occurredAt=" + occurredAt +
                '}';
    }
}
