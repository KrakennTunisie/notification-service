package com.kerp.notificationservice.infrastructure.adapters.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "billing-server", path = InternalApiPaths.ATTACHMENTS_BASE)
public interface BillingAttachmentClient {

    @GetMapping(InternalApiPaths.ATTACHMENT_CONTENT)
    byte[] getAttachmentContent(@PathVariable("id") UUID attachmentId);
}
