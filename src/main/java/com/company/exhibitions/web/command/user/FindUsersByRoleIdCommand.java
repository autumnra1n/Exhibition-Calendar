package com.company.exhibitions.web.command.user;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;

import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.role.RoleIdLengthValidator;
import com.company.exhibitions.web.controller.validation.role.RoleIdTypeValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdLengthValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdTypeValidator;
import com.company.exhibitions.web.controller.validation.user.UserPresenceChecker;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindUsersByRoleIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final UserPresenceChecker userPresenceChecker;
    private final IUserService userService;
    private final String pageUser;
    private final PaginationUtil paginationUtil;

    public FindUsersByRoleIdCommand(){
        this.controllerValidator = new RoleIdTypeValidator(
                                new RoleIdLengthValidator(
                                        new LimitTypeValidator(
                                                new LimitLengthValidator(
                                                        new CurrentPageTypeValidator(
                                                                new CurrentPageLengthValidator(
                                                                        null))))));
        this.userPresenceChecker = new UserPresenceChecker();
        this.userService = super.getServiceFactory().getUserService();
        this.pageUser = super.getPageManager().getProperty("page.user");
        this.paginationUtil = super.getPaginationUtil();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageUser;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Integer rows = userService.getNumberOfRowsByRoleId(parameters);
                paginationUtil.doPagination(parameters, request, rows);
                List<User> users = userService.findUsersByRoleId(parameters);
                return userPresenceChecker.checkUserListPresence(request, users, pageUser, pageUser);
            });
        }
    }
}
