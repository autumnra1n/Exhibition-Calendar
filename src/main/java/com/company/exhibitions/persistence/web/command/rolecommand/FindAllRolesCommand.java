package com.company.exhibitions.web.command.rolecommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAllRolesCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IRoleService roleService = serviceFactory.getRoleService();
        try {
            request.setAttribute("roles", roleService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.role");
    }
}
