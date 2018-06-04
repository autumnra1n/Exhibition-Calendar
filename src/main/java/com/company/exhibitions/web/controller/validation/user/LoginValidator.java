package com.company.exhibitions.web.controller.validation.user;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator extends ControllerValidator {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,32}$";

    public LoginValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        String login = map.get("login");
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(login);
        if(matcher.matches()){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_LOGIN.name().toLowerCase();
    }
}
