package com.company.exhibitions.web.controller.validation.userparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends ControllerValidator {

    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{3,64}$";

    @Override
    public String defineAttribute(Map<String, String> map) {
        String password = map.get("password");
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_PASSWORD.name().toLowerCase();
    }
}
