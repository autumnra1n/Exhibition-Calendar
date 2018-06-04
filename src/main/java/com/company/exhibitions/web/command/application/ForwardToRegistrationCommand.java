package com.company.exhibitions.web.command.application;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardToRegistrationCommand extends Command {

    private final PageManager pageManager;

    public ForwardToRegistrationCommand(){
        this.pageManager = super.getPageManager();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return pageManager.getProperty("page.registration");
    }
}
