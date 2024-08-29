package io.github.orionlibs.orion_email.template;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_cryptology.encoding.base64.Base64EncodingService;

public class EmailTemplateResolver
{
    public static String resolve(String velocityTemplateID)
    {
        String emailTemplate = InMemoryConfigurationService.getProp(velocityTemplateID);
        return Base64EncodingService.decodeBase64ForString(emailTemplate);
    }
}