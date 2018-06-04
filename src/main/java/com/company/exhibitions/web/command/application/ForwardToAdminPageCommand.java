package com.company.exhibitions.web.command.application;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AdminCredentials
public class ForwardToAdminPageCommand extends Command {

    private final String adminPage;

    public ForwardToAdminPageCommand() {
        this.adminPage = super.getPageManager().getProperty("page.admin");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return adminPage;
    }
}
