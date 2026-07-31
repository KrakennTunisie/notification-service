package com.kerp.notificationservice.infrastructure.adapters.out.messaging;

import com.kerp.notificationservice.application.ports.out.MailStatusPublisherPort;
import com.kerp.notificationservice.domain.model.MailSendResult;
import com.kerp.notificationservice.domain.records.MailStatusEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailStatusPublisherAdapter implements MailStatusPublisherPort {
    private final KafkaTemplate<String, MailStatusEventDto> kafkaTemplate;

    @Value("${mail.kafka.topic.mail-status:mail.status.events}")
    private String topic;

    @Override
    public void publish(MailSendResult result) {
        MailStatusEventDto dto = new MailStatusEventDto(
                result.getEventId(),
                result.getStatus().name(),
                result.getErrorMessage(),
                result.getProcessedAt()
        );
        log.info("resultat à envoyer : {}",dto);
        kafkaTemplate.send(topic, String.valueOf(result.getEventId()), dto);
    }
}
