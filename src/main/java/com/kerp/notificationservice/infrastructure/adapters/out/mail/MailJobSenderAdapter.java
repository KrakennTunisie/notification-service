package com.kerp.notificationservice.infrastructure.adapters.out.mail;

import com.kerp.notificationservice.application.ports.out.MailJobSenderPort;
import com.kerp.notificationservice.domain.records.Attachment;
import com.kerp.notificationservice.domain.records.MailJobMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailJobSenderAdapter implements MailJobSenderPort {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(MailJobMessage job, List<Attachment> attachments) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            boolean hasAttachments =
                    attachments!= null && !attachments.isEmpty();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, hasAttachments, "UTF-8");

            helper.setTo(job.toEmail());
            helper.setSubject(job.subject());
            helper.setText(toHtmlBody(job.body()), true);

            if (hasAttachments) {
                for (Attachment attachment : attachments) {
                    helper.addAttachment(
                            attachment.filename(),
                            new ByteArrayResource(attachment.content())
                    );
                }
            }

            javaMailSender.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException("Failed to prepare mail", e);
        } catch (MailException e) {
            throw e;
        }
    }

    private String toHtmlBody(String message) {
        if (message == null) {
            return "";
        }

        return message
                .replace("\r\n", "\n")
                .replace("\n", "<br />");
    }
}
