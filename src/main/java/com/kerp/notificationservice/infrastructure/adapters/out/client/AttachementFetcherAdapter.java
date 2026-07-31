package com.kerp.notificationservice.infrastructure.adapters.out.client;

import com.kerp.notificationservice.application.ports.out.AttachmentFetcherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AttachementFetcherAdapter implements AttachmentFetcherPort {

    private final BillingAttachmentClient billingAttachmentClient;
    @Override
    public byte[] fetchContent(UUID attachmentId) {
        return billingAttachmentClient.getAttachmentContent(attachmentId);
    }
}
