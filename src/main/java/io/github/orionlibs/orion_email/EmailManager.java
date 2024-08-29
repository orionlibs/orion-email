package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_assert.Assert;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

class EmailManager
{
    static boolean sendEmail(EmailData emailData) throws EmailerException
    {
        Assert.notNull(emailData, "The given emailData input cannot be null");
        EmailSanitiser.sanitise(emailData);
        Properties emailParameters = EmailConfigurator.getEmailParameters();
        Session emailSession = Session.getInstance(emailParameters);
        EmailMessageDependencies emailDependencies = EmailMessageDependenciesBuilder.buildDependencies(emailSession, emailData);
        MimeMessage messageToSend = EmailMessageBuilder.buildMessage(emailDependencies);
        try
        {
            return EmailSender.sendEmail(EmailSenderDataBuilder.build(emailSession, messageToSend));
        }
        catch(NoSuchProviderException e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
        catch(Exception e)
        {
            throw new EmailerException(e, "there was a problem with the emailer.");
        }
    }
}