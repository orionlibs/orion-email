package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.SendFailedException;
import jakarta.mail.Transport;

public class EmailSender
{
    public static boolean sendEmail(EmailSenderData senderData) throws EmailerException
    {
        boolean emailWasSentSuccessfully = false;
        try
        {
            Transport transport = senderData.getTransport();
            transport.connect(senderData.getSMTPHost(), InMemoryConfigurationService.getIntegerProp("email.administrator.email.address.smtp.port"), senderData.getEmailAccountUsername(), senderData.getEmailAccountPassword());
            transport.sendMessage(senderData.getMessageToSend(), senderData.getMessageToSend().getAllRecipients());
            transport.close();
            emailWasSentSuccessfully = true;
        }
        catch(NoSuchProviderException e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        catch(SendFailedException e)
        {
            throw new EmailerException(e, "I could not send the email.");
        }
        catch(MessagingException e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        catch(Exception e)
        {
            throw new EmailerException(e, "there was some problem with the emailer.");
        }
        return emailWasSentSuccessfully;
    }
}