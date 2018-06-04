package com.company.exhibitions.web.command.user;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdLengthValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdTypeValidator;
import com.company.exhibitions.web.controller.validation.user.UserPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindUserByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final UserPresenceChecker userPresenceChecker;
    private final IUserService userService;
    private final String pageUser;

    public FindUserByIdCommand(){
        this.controllerValidator = new UserIdTypeValidator(new UserIdLengthValidator(null));
        this.userService = super.getServiceFactory().getUserService();
        this.userPresenceChecker = new UserPresenceChecker();
        this.pageUser = super.getPageManager().getProperty("page.user");
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
                User user = userService.findUserById(parameters);
                return userPresenceChecker.checkUserPresence(request, user, pageUser, pageUser);
            });
        }
    }
}
