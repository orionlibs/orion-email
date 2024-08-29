package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_email.template.InitialEmailTemplateParametersProvider;
import java.util.Map;

public class EmailService
{
    public static EmailData prepareEmailFromTemplate(Map<String, String> templateParameters, String velocityTemplateID, String velocityTemplateName, String emailSubject, String emailRecipient, String emailSender, String emailSenderName)
    {
        return EmailDataPopulator.populate(templateParameters, velocityTemplateID, velocityTemplateName, emailSubject, emailRecipient, emailSender, emailSenderName);
    }


    public static Map<String, String> getInitialTemplateParameters()
    {
        return InitialEmailTemplateParametersProvider.initialiseTemplateParameters();
    }


    public static boolean sendEmail(EmailData emailData) throws EmailerException
    {
        if(InMemoryConfigurationService.getBooleanProp("enable.emailer", true))
        {
            return EmailManager.sendEmail(emailData);
        }
        else
        {
            return true;
        }
    }
}