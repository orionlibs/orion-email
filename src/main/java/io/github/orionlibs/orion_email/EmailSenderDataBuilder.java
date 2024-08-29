package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

class EmailSenderDataBuilder
{
    static EmailSenderData build(Session emailSession, MimeMessage messageToSend) throws NoSuchProviderException
    {
        return EmailSenderData.builder()
                        .transport(emailSession.getTransport("smtp"))
                        .messageToSend(messageToSend)
                        .SMTPHost(InMemoryConfigurationService.getProp("email.administrator.email.address.smtp.host"))
                        .emailAccountUsername(InMemoryConfigurationService.getProp("email.administrator.email.address.username"))
                        .emailAccountPassword(InMemoryConfigurationService.getProp("email.administrator.email.address.password"))
                        .build();
    }
}