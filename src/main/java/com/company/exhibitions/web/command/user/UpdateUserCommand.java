package com.company.exhibitions.web.command.user;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
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

@AdminCredentials
public class UpdateUserCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final UserPresenceChecker userPresenceChecker;
    private final RolePresenceChecker rolePresenceChecker;
    private final IUserService userService;
    private final IRoleService roleService;
    private final String pageAdmin;

    public UpdateUserCommand(){
        this.controllerValidator = new UserIdTypeValidator(
                new UserIdLengthValidator(
                        new LoginValidator(
                                new PasswordValidator(
                                        new EmailValidator(
                                                new FirstNameValidator(
                                                        new LastNameValidator(
                                                                new RoleIdTypeValidator(
                                                                        new RoleIdLengthValidator(
                                                                                null)))))))));
        this.userPresenceChecker = new UserPresenceChecker();
        this.rolePresenceChecker = new RolePresenceChecker();
        this.userService = super.getServiceFactory().getUserService();
        this.roleService = super.getServiceFactory().getRoleService();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                Role role = roleService.findRoleById(parameters);
                User user = userService.findUserById(parameters);
                rolePresenceChecker.checkRolePresence(request, role, pageAdmin, pageAdmin);
                userPresenceChecker.checkUserPresence(request, user, pageAdmin, pageAdmin);
            });
            if (Objects.nonNull(request.getAttribute(CustomRequestAttributes.USER.name().toLowerCase()))&&
                    Objects.nonNull(request.getAttribute(CustomRequestAttributes.ROLE.name().toLowerCase()))) {

                super.getControllerExecutor().perform(() -> userService.updateUser(parameters));
            }
            return pageAdmin;
        }
    }
}
