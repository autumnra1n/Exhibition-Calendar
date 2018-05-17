package com.company.exhibitions.web.command.rolecommand.impl;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.rolecommand.RoleCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.RoleParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertRoleCommand extends RoleCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public InsertRoleCommand(){
        RoleParametersValidatorFactory roleParametersValidatorFactory = new RoleParametersValidatorFactory();
        this.controllerValidator = roleParametersValidatorFactory.getRoleNameValidator();
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IRoleService roleService = serviceFactory.getRoleService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() ->{
                Role role = roleService.findRole(parameters);
                checkRolePresence(request, role);
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.ROLE.name().toLowerCase()))) {
                controllerExecutor.perform(() -> {
                    roleService.insertRole(extractParameters(request));
                });
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
