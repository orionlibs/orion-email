package io.github.orionlibs.orion_email;

import jakarta.mail.Session;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
class EmailMessageDependenciesBuilder
{
    static EmailMessageDependencies buildDependencies(Session emailSession, EmailData emailData) throws EmailerException
    {
        return EmailMessageDependencies.builder()
                        .emailSession(emailSession)
                        .emailSender(emailData.getEmailSender())
                        .emailSenderName(emailData.getEmailSenderName())
                        .emailRecipient(emailData.getEmailRecipient())
                        .emailSubject(emailData.getEmailSubject())
                        .emailMessage(emailData.getEmailMessage())
                        .messageMIMEType(emailData.getMessageMIMEType())
                        .hasAttachment(emailData.isHasAttachment())
                        .attachmentFileURL(emailData.getAttachmentFileURL())
                        .attachmentFileName(emailData.getAttachmentFileName())
                        .loadAttachmentFromFileSystem(emailData.isLoadAttachmentFromFileSystem())
                        .build();
    }
}