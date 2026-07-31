package com.kerp.notificationservice.application.ports.out;

import java.util.UUID;

public interface AttachmentFetcherPort {
    byte[] fetchContent(UUID attachmentId);

}
