package io.github.orionlibs.orion_email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailData
{
    private String emailID;
    private String emailSender;
    private String emailSenderName;
    private String emailRecipient;
    private String emailSubject;
    private String emailMessage;
    private String messageMIMEType;
    private boolean replaceNewLineWithBreakLine;
    private boolean hasAttachment;
    private boolean loadAttachmentFromFileSystem;
    private String attachmentFileURL;
    private String attachmentFileName;
}