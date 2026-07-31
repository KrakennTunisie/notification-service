package com.kerp.notificationservice.domain.model;

import com.kerp.notificationservice.domain.enums.MailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class MailSendResult {
    private UUID eventId;
    private MailStatus status;
    private String errorMessage;
    private Instant processedAt;
}
