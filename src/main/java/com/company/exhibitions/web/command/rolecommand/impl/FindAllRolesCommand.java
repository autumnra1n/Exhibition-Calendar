package com.company.exhibitions.web.command.rolecommand.impl;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.rolecommand.RoleCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllRolesCommand extends RoleCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllRolesCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IRoleService roleService = serviceFactory.getRoleService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<Role> roles = roleService.findAll();
            return checkRoleListPresence(request, roles);
        });
    }
}
