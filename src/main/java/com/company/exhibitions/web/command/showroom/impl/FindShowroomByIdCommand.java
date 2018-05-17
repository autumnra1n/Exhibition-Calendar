package com.company.exhibitions.web.command.showroom.impl;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.showroom.ShowroomCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class FindShowroomByIdCommand extends ShowroomCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public FindShowroomByIdCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        this.controllerValidator = idTypeValidator.linkWith(idLengthValidator);
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IShowroomService showroomService = serviceFactory.getShowroomService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.showroom");
        } else {
            return controllerExecutor.performAttributeSelect(() -> {
                Showroom showroom = showroomService.findShowroomById(parameters);
                return checkShowroomPresence(request, showroom);
            });
        }
    }
}
