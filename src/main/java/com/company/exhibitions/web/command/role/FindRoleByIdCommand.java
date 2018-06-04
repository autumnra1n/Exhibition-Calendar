package com.company.exhibitions.web.command.role;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.role.RoleIdLengthValidator;
import com.company.exhibitions.web.controller.validation.role.RoleIdTypeValidator;
import com.company.exhibitions.web.controller.validation.role.RolePresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindRoleByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final RolePresenceChecker rolePresenceChecker;
    private final IRoleService roleService;
    private final String pageRole;

    public FindRoleByIdCommand(){
        controllerValidator = new RoleIdTypeValidator(new RoleIdLengthValidator(null));
        this.rolePresenceChecker = new RolePresenceChecker();
        this.roleService = super.getServiceFactory().getRoleService();
        this.pageRole = super.getPageManager().getProperty("page.role");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageRole;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Role role = roleService.findRoleById(parameters);
                return rolePresenceChecker.checkRolePresence(request, role, pageRole, pageRole);
            });
        }
    }
}
