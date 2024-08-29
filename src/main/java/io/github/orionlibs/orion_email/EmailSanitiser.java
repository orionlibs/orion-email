package io.github.orionlibs.orion_email;

import io.github.orionlibs.orion_email.template.EmailTemplateDefaults;

class EmailSanitiser
{
    static void sanitise(EmailData emailData)
    {
        if(emailData.isReplaceNewLineWithBreakLine() && EmailTemplateDefaults.messageMIMEType.equals(emailData.getMessageMIMEType()))
        {
            emailData.setEmailMessage(emailData.getEmailMessage().replace("\n", "<br>"));
        }
    }
}