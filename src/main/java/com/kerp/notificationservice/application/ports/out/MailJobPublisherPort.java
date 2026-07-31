package com.kerp.notificationservice.application.ports.out;


import com.kerp.notificationservice.domain.model.MailJob;
import com.kerp.notificationservice.domain.records.Attachment;

import java.util.List;

public interface MailJobPublisherPort {
    void publish(MailJob job, List<Attachment> contents);

}
