package com.company.exhibitions.web.command.role;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.role.RolePresenceChecker;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindAllRolesCommand extends Command {

    private final IRoleService roleService;
    private final RolePresenceChecker rolePresenceChecker;
    private final String pageRole;
    private final PaginationUtil paginationUtil;
    private final ControllerValidator controllerValidator;

    public FindAllRolesCommand(){
        this.roleService = super.getServiceFactory().getRoleService();
        this.rolePresenceChecker = new RolePresenceChecker();
        this.pageRole = super.getPageManager().getProperty("page.role");
        this.paginationUtil = super.getPaginationUtil();
        this.controllerValidator = new LimitTypeValidator(
                new LimitLengthValidator(
                        new CurrentPageTypeValidator(
                                new CurrentPageLengthValidator(
                                        null))));
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
                Integer rows = roleService.getNumberOfRows();
                paginationUtil.doPagination(parameters, request, rows);
                List<Role> roles = roleService.findAll(parameters);
                return rolePresenceChecker.checkRoleListPresence(request, roles, pageRole, pageRole);
            });
        }
    }
}
