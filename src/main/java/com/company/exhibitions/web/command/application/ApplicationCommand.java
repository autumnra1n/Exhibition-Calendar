package com.company.exhibitions.web.command.application;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public abstract class ApplicationCommand extends Command{

    public String checkLoginExistance(HttpServletRequest request, User user, Map<String, String> parameters){
        if(Objects.isNull(user)){
            request.setAttribute(CustomRequestAttributes.INVALID_LOGIN.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.login");
        }
        else {
            if(user.getLogin().equals(parameters.get("login"))){
                if(user.getPassword().equals(parameters.get("password"))){
                    request.setAttribute(CustomRequestAttributes.LOGIN.name().toLowerCase(), user);
                    return PageManager.getProperty("page.exposition");
                }
                else {
                    request.setAttribute(CustomRequestAttributes.INVALID_PASSWORD.name().toLowerCase(), new Object());
                    return PageManager.getProperty("page.login");
                }

            }
            else {
                request.setAttribute(CustomRequestAttributes.INVALID_LOGIN.name().toLowerCase(), new Object());
                return PageManager.getProperty("page.login");
            }
        }
    }
}
