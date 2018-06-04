package com.company.exhibitions.web.command.application;

import com.company.exhibitions.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UndefinedCommand extends Command {

    private final String pageLogin;
    private final String pageExposition;

    public UndefinedCommand(){
        this.pageLogin = super.getPageManager().getProperty("page.login");
        this.pageExposition = super.getPageManager().getProperty("page.exposition");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(Objects.isNull(request.getSession().getAttribute("user"))){
            return pageLogin;
        }else
            return pageExposition;
    }
}
