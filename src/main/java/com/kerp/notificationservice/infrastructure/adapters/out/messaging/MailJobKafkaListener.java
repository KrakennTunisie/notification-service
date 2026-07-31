package com.kerp.notificationservice.infrastructure.adapters.out.messaging;

import com.kerp.notificationservice.application.ports.in.SendMailUseCase;
import com.kerp.notificationservice.domain.model.MailJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailJobKafkaListener {

    private final SendMailUseCase sendMailUseCase;

    @KafkaListener(
            topics = {
                    "${mail.kafka.topic.billing:billing.mail.events}",
                    "${mail.kafka.topic.iam:iam.mail.events}"
            },
            groupId = "notification-service"
    )
    public void onMessage(MailJob dto) {
        log.info("Received mail job event {} (type={})", dto.getEventId(), dto.getEventType());
        sendMailUseCase.handleMailEvent(dto);
    }
}