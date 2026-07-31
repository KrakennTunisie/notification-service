package com.kerp.notificationservice.application.services;

import com.kerp.notificationservice.application.ports.in.SendMailUseCase;
import com.kerp.notificationservice.application.ports.out.*;
import com.kerp.notificationservice.domain.model.MailAttachmentMetadata;
import com.kerp.notificationservice.domain.model.MailJob;
import com.kerp.notificationservice.domain.records.Attachment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class SendMailService implements SendMailUseCase {

    private final ProcessedEventPort processedJobPort;

    private final MailJobPublisherPort mailJobPublisherPort;

    private final AttachmentFetcherPort attachmentFetcherPort;


    @Override
    public void handleMailEvent(MailJob mailJob) {
        if (processedJobPort.isAlreadyProcessed(mailJob.getEventId())) {
            log.info("Event {} already processed, skipping", mailJob.getEventId());
            return;
        }

        try {
            List<Attachment> contents = fetchAttachments(mailJob);

            mailJobPublisherPort.publish(mailJob, contents);

        } catch (Exception e) {
            log.error("Failed to send mail for job {}", mailJob.getEventId(), e);

        }
    }

    private List<Attachment> fetchAttachments(MailJob job) {
        if(job.getAttachments().isEmpty()){
            return List.of();
        }
        List<Attachment> attachmentsData = new ArrayList<>();
        for (MailAttachmentMetadata mailAttachmentMetadata: job.getAttachments()){
            log.info("mailAttachmentMetadata: {}",mailAttachmentMetadata);
            Attachment attachment = new Attachment(
                    mailAttachmentMetadata.getFileName(),
                    mailAttachmentMetadata.getFilePath(),
                    mailAttachmentMetadata.getContentType(),
                    attachmentFetcherPort.fetchContent(UUID.fromString(mailAttachmentMetadata.getIdDocument())
                    ));
            attachmentsData.add(attachment);
        }
         return  attachmentsData;

    }
}
