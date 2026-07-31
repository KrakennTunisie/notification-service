package com.kerp.notificationservice.domain.records;

public record Attachment(
        String filename,
        String filePath,
        String contentType,
        byte[] content
) {
}
