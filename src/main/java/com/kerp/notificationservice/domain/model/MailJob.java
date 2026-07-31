package com.kerp.notificationservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MailJob {
    private String eventId;
    private String eventType;       // INVOICE_CREATED, PAYMENT_RECEIVED, etc.
    private String toEmail;
    private String subject;
    private String body;
    private List<MailAttachmentMetadata> attachments;
    private LocalDateTime occurredAt;
}
