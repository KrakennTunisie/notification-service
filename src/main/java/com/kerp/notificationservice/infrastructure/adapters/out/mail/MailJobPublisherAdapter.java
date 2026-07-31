package com.kerp.notificationservice.infrastructure.adapters.out.mail;

import com.kerp.notificationservice.application.ports.out.MailJobPublisherPort;
import com.kerp.notificationservice.domain.model.MailJob;
import com.kerp.notificationservice.domain.records.Attachment;
import com.kerp.notificationservice.domain.records.MailJobMessage;
import com.kerp.notificationservice.infrastructure.config.EmailQueueConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class MailJobPublisherAdapter implements MailJobPublisherPort {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(MailJob job, List<Attachment> contents) {
        log.info("Publishing mail job. to={}, subject={}", job.getToEmail(), job.getSubject());

        MailJobMessage message = new MailJobMessage(
                job.getEventId(),
                job.getEventType(),
                job.getToEmail(),
                job.getSubject(),
                job.getBody(),
                job.getOccurredAt(),
                contents
        );


        rabbitTemplate.convertAndSend(
                EmailQueueConfig.EXCHANGE,
                EmailQueueConfig.ROUTING_KEY,
                message
        );

        log.info("Mail job published. to={}", job.getToEmail());
    }
}
