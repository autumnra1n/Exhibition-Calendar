package com.company.exhibitions.web.command.showroomcommand.impl;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.showroomcommand.ShowroomCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.controller.validation.factories.ShowroomParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertShowroomCommand extends ShowroomCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public InsertShowroomCommand(){
        ShowroomParametersValidatorFactory showroomParametersValidatorFactory = new ShowroomParametersValidatorFactory();
        ControllerValidator showroomLocationValidator = showroomParametersValidatorFactory.getShowroomLocationValidator();
        ControllerValidator showroomNameValidator = showroomParametersValidatorFactory.getShowroomNameValidator();
        this.controllerValidator = showroomLocationValidator.linkWith(showroomNameValidator);
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
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() -> {
                Showroom showroom = showroomService.findShowroom(parameters);
                checkShowroomPresence(request, showroom);
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase()))) {
                controllerExecutor.perform(() -> {
                    showroomService.insertShowroom(parameters);
                });
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
