package com.kerp.notificationservice.domain.records;



import java.time.LocalDateTime;
import java.util.List;

public record MailJobMessage(
        String eventId,
        String eventType,       // INVOICE_CREATED, PAYMENT_RECEIVED, etc.
        String toEmail,
        String subject,
        String body,
        LocalDateTime occurredAt,
        List<Attachment> attachments
) {
}
