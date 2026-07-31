package com.kerp.notificationservice.infrastructure.adapters.out.mail;

import com.kerp.notificationservice.application.ports.out.*;
import com.kerp.notificationservice.domain.enums.MailStatus;
import com.kerp.notificationservice.domain.model.MailSendResult;
import com.kerp.notificationservice.domain.records.MailJobMessage;
import com.kerp.notificationservice.infrastructure.config.EmailQueueConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class EmailJobConsumer {
    private final MailJobSenderPort emailSenderPort;

    private final ProcessedEventPort processedJobPort;

    private final MailStatusPublisherPort mailStatusPublisherPort;

    @RabbitListener(queues = EmailQueueConfig.QUEUE)
    public void consume(MailJobMessage mailJobMessage) {

        log.info("Consuming mail job. to={}, subject={}", mailJobMessage.toEmail(), mailJobMessage.subject());

        try {
            emailSenderPort.sendEmail(mailJobMessage, mailJobMessage.attachments());
            log.info("Mail sent successfully. to={}, subject={}", mailJobMessage.toEmail(), mailJobMessage.subject());

            processedJobPort.markAsProcessed(mailJobMessage.eventId());

            mailStatusPublisherPort.publish(
                    new MailSendResult(UUID.fromString(mailJobMessage.eventId()), MailStatus.SENT, null, Instant.now())
            );
        } catch (Exception e) {
            log.error("Mail sending failed. to={}, subject={}, error={}",
                    mailJobMessage.toEmail(), mailJobMessage.subject(), e.getMessage(), e);

            // On marque quand même comme "processed" pour ne pas boucler indéfiniment
            // sur le même message Kafka en cas de retry consumer — le retry métier
            // est géré côté billing-service via son propre outbox retryCount.
            processedJobPort.markAsProcessed(mailJobMessage.eventId());

            mailStatusPublisherPort.publish(
                    new MailSendResult(UUID.fromString(mailJobMessage.eventId()), MailStatus.FAILED, e.getMessage(), Instant.now())
            );
        }
    }
}
