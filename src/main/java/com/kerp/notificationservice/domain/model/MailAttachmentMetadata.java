package com.kerp.notificationservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MailAttachmentMetadata {
    private UUID id;

    private String idDocument;

    private String fileName;

    private String filePath;

    private String contentType;

    @Override
    public String toString() {
        return "MailAttachmentMetadata{" +
                "id=" + id +
                ", idDocument='" + idDocument + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
