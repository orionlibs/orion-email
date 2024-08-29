package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_file_system.file.FileService;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class EmailMessageBuilder
{
    static MimeMessage buildMessage(EmailMessageDependencies dependencies) throws EmailerException
    {
        MimeMessage messageToSend = new MimeMessage(dependencies.getEmailSession());
        try
        {
            messageToSend.setFrom(new InternetAddress(dependencies.getEmailSender(), dependencies.getEmailSenderName()));
            messageToSend.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dependencies.getEmailRecipient(), false));
            messageToSend.setSubject(dependencies.getEmailSubject());
            messageToSend.setSentDate(new Date());
            if(dependencies.isHasAttachment())
            {
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(dependencies.getEmailMessage(), dependencies.getMessageMIMEType());
                MimeBodyPart attachmentPart = new MimeBodyPart();
                try
                {
                    File fileToAttach = null;
                    if(dependencies.isLoadAttachmentFromFileSystem())
                    {
                        fileToAttach = new File(dependencies.getAttachmentFileURL());
                    }
                    else
                    {
                        URL url = new URL(dependencies.getAttachmentFileURL());
                        String tempFolder = InMemoryConfigurationService.getProp("file.upload.temporary.path.on.aws.ec2.tomcat");
                        try
                        {
                            FileService.deleteFile(tempFolder + dependencies.getAttachmentFileName());
                        }
                        catch(IOException e)
                        {
                            //
                        }
                        fileToAttach = new File(tempFolder + dependencies.getAttachmentFileName());
                        FileUtils.copyURLToFile(url, fileToAttach);
                    }
                    attachmentPart.attachFile(fileToAttach);
                    attachmentPart.setDisposition("attachment; filename=" + dependencies.getAttachmentFileName());
                    attachmentPart.setFileName(dependencies.getAttachmentFileName());
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    multipart.addBodyPart(attachmentPart);
                    messageToSend.setContent(multipart);
                }
                catch(IOException e)
                {
                    throw new EmailerException(e, "there was a problem with the emailer.");
                }
            }
            else
            {
                messageToSend.setContent(dependencies.getEmailMessage(), dependencies.getMessageMIMEType());
            }
        }
        catch(MessagingException e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        catch(UnsupportedEncodingException e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        catch(Exception e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        return messageToSend;
    }
}