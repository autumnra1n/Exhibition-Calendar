package com.company.exhibitions.web.command.application;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.UserCredentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@UserCredentials
public class LogoutCommand extends Command {

    private final String pageLogin;

    public LogoutCommand(){
        this.pageLogin = super.getPageManager().getProperty("page.login");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(Objects.nonNull(request.getSession().getAttribute("user"))){
            request.getSession().invalidate();
        }
        return pageLogin;
    }
}
