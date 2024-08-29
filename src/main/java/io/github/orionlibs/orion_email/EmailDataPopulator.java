package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_email.template.EmailTemplateDefaults;
import io.github.orionlibs.orion_email.template.EmailTemplateResolver;
import io.github.orionlibs.orion_velocity.VelocityTemplateService;
import java.util.Map;

public class EmailDataPopulator
{
    public static EmailData populate(Map<String, String> templateParameters, String velocityTemplateID, String velocityTemplateName, String emailSubject, String emailRecipient, String emailSender, String emailSenderName)
    {
        String decodedHTMLTemplate = EmailTemplateResolver.resolve(velocityTemplateID);
        String templateBody = VelocityTemplateService.getVelocityTemplateAsString(templateParameters, velocityTemplateName, decodedHTMLTemplate);
        return EmailData.builder()
                        .emailID(templateParameters.get("emailID"))
                        .emailMessage(templateBody)
                        .emailRecipient(emailRecipient)
                        .emailSender(emailSender)
                        .emailSenderName(emailSenderName)
                        .emailSubject(emailSubject)
                        .messageMIMEType(EmailTemplateDefaults.messageMIMEType)
                        .build();
    }
}