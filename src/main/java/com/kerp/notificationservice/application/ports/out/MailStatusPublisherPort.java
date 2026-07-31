package com.kerp.notificationservice.application.ports.out;

import com.kerp.notificationservice.domain.model.MailSendResult;

public interface MailStatusPublisherPort {
    void publish(MailSendResult result);

}
