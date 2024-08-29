package io.github.orionlibs.orion_email.template;

import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_object.UUIDSecurityService;
import java.util.HashMap;
import java.util.Map;

public class InitialEmailTemplateParametersProvider
{
    public static Map<String, String> initialiseTemplateParameters()
    {
        return generateInitialTemplateParameters();
    }


    private static Map<String, String> generateInitialTemplateParameters()
    {
        Map<String, String> templateParameters = new HashMap<>();
        templateParameters.put("emailID", UUIDSecurityService.generateUUIDWithoutHyphens());
        templateParameters.put("currentYear", Integer.toString(CalendarService.getCurrentYear()));
        return templateParameters;
    }
}