package com.company.exhibitions.web.command.application;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.application.*;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionPresenceChecker;
import com.company.exhibitions.web.controller.validation.user.LoginValidator;
import com.company.exhibitions.web.controller.validation.user.PasswordValidator;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginCommand extends Command {

    private final String pageExhibition;
    private final String pageLogin;
    private final ControllerValidator controllerValidator;
    private final ProfilePresenceChecker profilePresenceChecker;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final IUserService userService;
    private final IExpositionService expositionService;
    private final PaginationUtil paginationUtil;

    public LoginCommand(){
        this.controllerValidator = new LoginValidator(
                new PasswordValidator(
                        new LimitTypeValidator(
                            new LimitLengthValidator(
                                new CurrentPageTypeValidator(
                                    new CurrentPageLengthValidator(
                                        null))))));
        this.profilePresenceChecker = new ProfilePresenceChecker();
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.pageExhibition = super.getPageManager().getProperty("page.exposition");
        this.pageLogin = super.getPageManager().getProperty("page.login");
        this.userService = super.getServiceFactory().getUserService();
        this.expositionService = super.getServiceFactory().getExpositionService();
        this.paginationUtil = super.getPaginationUtil();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageLogin;
        } else {
            if(Objects.nonNull(request.getSession().getAttribute("user"))){
                request.getSession().invalidate();
            }
            return super.getControllerExecutor().performAttributeSelect(() ->{
                User user = userService.findAccount(parameters);
                profilePresenceChecker.checkProfileExistence(request, user, parameters);
                if(Objects.nonNull(request.getAttribute("login"))){
                    request.getSession().setAttribute("user", user);
                    return super.getControllerExecutor().performAttributeSelect(() ->{
                        System.out.println(expositionService.getNumberOfRows());
                        paginationUtil.doPagination(parameters, request, expositionService.getNumberOfRows());
                        List<Exposition> expositions = expositionService.findAll(parameters);
                        return expositionPresenceChecker.checkExpositionListPresence(request, expositions,
                                pageExhibition, pageExhibition);
                    });
                }
                else
                    return pageLogin;
            });
        }
    }
}
