package io.github.orionlibs.orion_email;

import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailSenderData
{
    private Transport transport;
    private MimeMessage messageToSend;
    private String SMTPHost;
    private String emailAccountUsername;
    private String emailAccountPassword;
}