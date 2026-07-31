package com.kerp.notificationservice.application.ports.in;

import com.kerp.notificationservice.domain.model.MailJob;

public interface SendMailUseCase {
    void handleMailEvent(MailJob mailJob);
}
