package com.kerp.notificationservice.application.ports.out;

import com.kerp.notificationservice.domain.records.Attachment;
import com.kerp.notificationservice.domain.records.MailJobMessage;

import java.util.List;

public interface MailJobSenderPort {
    void sendEmail(MailJobMessage mailJobMessage, List<Attachment> attachments);
}
