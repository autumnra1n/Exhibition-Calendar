package com.company.exhibitions.web.command.usercommand.impl;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.usercommand.UserCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllUsersCommand extends UserCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllUsersCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<User> users = userService.findAll();
            return checkUserListPresence(request, users);
        });
    }
}
