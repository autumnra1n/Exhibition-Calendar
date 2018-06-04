package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.utils.Base64Encoder;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class ProfilePresenceChecker {

    public void checkProfileExistence(HttpServletRequest request, User user, Map<String, String> parameters) {
        String password = parameters.get("password");
        if (Objects.isNull(user)) {
            request.setAttribute(CustomRequestAttributes.INVALID_LOGIN.name().toLowerCase(), new Object());
        } else {
            if (user.getLogin().equals(parameters.get("login"))) {
                if (user.getPassword().equals(password)) {
                    request.setAttribute(CustomRequestAttributes.LOGIN.name().toLowerCase(), user);
                } else {
                    request.setAttribute(CustomRequestAttributes.INVALID_PASSWORD.name().toLowerCase(), new Object());
                }

            } else {
                request.setAttribute(CustomRequestAttributes.INVALID_LOGIN.name().toLowerCase(), new Object());
            }
        }
    }
}
