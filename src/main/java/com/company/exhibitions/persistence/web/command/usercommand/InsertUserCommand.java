package com.company.exhibitions.web.command.usercommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUserCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        try {
            userService.insertUser(extractParameters(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.admin");
    }
}
