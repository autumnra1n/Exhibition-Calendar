package com.company.exhibitions.web.command.applicationcommand.impl;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.applicationcommand.ApplicationCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.UserParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutable;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class LoginCommand extends ApplicationCommand{

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public LoginCommand(){
        UserParametersValidatorFactory userParametersValidatorFactory = new UserParametersValidatorFactory();
        this.controllerExecutor = new ControllerExecutor();
        this.controllerValidator = userParametersValidatorFactory.getLoginValidator()
                .linkWith(userParametersValidatorFactory.getPasswordValidator());
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.login");
        } else {
            return controllerExecutor.performAttributeSelect(() ->{
                User user = userService.findAccount(parameters);
                return checkLoginExistance(request, user, parameters);});
        }
    }
}
