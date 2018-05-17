package com.company.exhibitions.web.command.usercommand.impl;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.usercommand.UserCommand;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.UserParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertUserCommand extends UserCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public InsertUserCommand(){
        this.controllerExecutor = new ControllerExecutor();
        UserParametersValidatorFactory userParametersValidatorFactory = new UserParametersValidatorFactory();
        ControllerValidator loginValidator = userParametersValidatorFactory.getLoginValidator();
        ControllerValidator passwordValidator = userParametersValidatorFactory.getPasswordValidator();
        ControllerValidator emailValidator = userParametersValidatorFactory.getEmailValidator();
        ControllerValidator firstNameValidator = userParametersValidatorFactory.getFirstNameValidator();
        ControllerValidator lastNameValidator = userParametersValidatorFactory.getLastNameValidator();
        this.controllerValidator = loginValidator
                .linkWith(passwordValidator)
                .linkWith(emailValidator)
                .linkWith(firstNameValidator)
                .linkWith(lastNameValidator);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() -> {
                User user = userService.findAccount(parameters);
                checkUserPresence(request, user);
            });
            return PageManager.getProperty("page.admin");
        }
    }
}
