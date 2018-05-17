package com.company.exhibitions.web.controller.validation.userparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends ControllerValidator {

    private static final String EMAIL_PATTERN = "^(\\S+)@(\\S+)\\.(\\S+)$";

    @Override
    public String defineAttribute(Map<String, String> map) {
        String email = map.get("email");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(email.length()<64&&matcher.matches()){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_EMAIL.name().toLowerCase();
    }
}
