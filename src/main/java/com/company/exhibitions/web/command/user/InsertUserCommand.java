package com.company.exhibitions.web.command.user;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.role.RoleIdLengthValidator;
import com.company.exhibitions.web.controller.validation.role.RoleIdTypeValidator;
import com.company.exhibitions.web.controller.validation.role.RolePresenceChecker;
import com.company.exhibitions.web.controller.validation.user.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertUserCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final UserPresenceChecker userPresenceChecker;
    private final RolePresenceChecker rolePresenceChecker;
    private final IUserService userService;
    private final IRoleService roleService;
    private final String pageRegistration;
    private final String pageLogin;

    public InsertUserCommand(){
        this.controllerValidator = new LoginValidator(
                new PasswordValidator(
                        new FirstNameValidator(
                                new LastNameValidator(
                                        new EmailValidator(
                                                null)))));
        this.userPresenceChecker = new UserPresenceChecker();
        this.rolePresenceChecker = new RolePresenceChecker();
        this.userService = super.getServiceFactory().getUserService();
        this.roleService = super.getServiceFactory().getRoleService();
        this.pageLogin = super.getPageManager().getProperty("page.login");
        this.pageRegistration = super.getPageManager().getProperty("page.registration");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageRegistration;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                User user = userService.findAccount(parameters);
                parameters.put("roleName", "user");
                Role role = roleService.findRole(parameters);
                parameters.put("roleId", String.valueOf(role.getId()));
                userPresenceChecker.checkUserPresence(request, user, pageRegistration, pageLogin);
                rolePresenceChecker.checkRolePresence(request, role, pageRegistration, pageLogin);
                if (Objects.isNull(request.getAttribute(CustomRequestAttributes.USER.name().toLowerCase()))&&
                        Objects.nonNull(request.getAttribute(CustomRequestAttributes.ROLE.name().toLowerCase()))) {
                    super.getControllerExecutor().perform(() -> userService.insertUser(parameters));
                    return pageLogin;
                }
                else
                    return pageRegistration;
            });
        }
    }
}
