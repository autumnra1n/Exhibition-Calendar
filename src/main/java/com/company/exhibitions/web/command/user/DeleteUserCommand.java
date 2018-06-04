package com.company.exhibitions.web.command.user;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.user.UserIdLengthValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdTypeValidator;
import com.company.exhibitions.web.controller.validation.user.UserPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class DeleteUserCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final IUserService userService;
    private final UserPresenceChecker userPresenceChecker;
    private final String pageAdmin;

    public DeleteUserCommand(){
        this.controllerValidator = new UserIdTypeValidator(new UserIdLengthValidator(null));
        this.userService = super.getServiceFactory().getUserService();
        this.userPresenceChecker = new UserPresenceChecker();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                User user = userService.findUserById(parameters);
                userPresenceChecker.checkUserPresence(request, user, pageAdmin, pageAdmin);
            });
            if(Objects.nonNull(request.getAttribute(CustomRequestAttributes.USER.name().toLowerCase()))){
                super.getControllerExecutor().perform(() -> userService.deleteUser(parameters));
            }
            return pageAdmin;
        }
    }
}
