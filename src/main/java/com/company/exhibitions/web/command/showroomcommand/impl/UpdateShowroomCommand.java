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

public class UpdateShowroomCommand extends ShowroomCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public UpdateShowroomCommand(){
        ShowroomParametersValidatorFactory showroomParametersValidatorFactory = new ShowroomParametersValidatorFactory();
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator showroomLocationValidator = showroomParametersValidatorFactory.getShowroomLocationValidator();
        ControllerValidator showroomNameValidator = showroomParametersValidatorFactory.getShowroomNameValidator();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        this.controllerExecutor = new ControllerExecutor();
        this.controllerValidator = idTypeValidator
                .linkWith(idLengthValidator)
                .linkWith(showroomLocationValidator)
                .linkWith(showroomNameValidator);
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
            if (!Objects.isNull(request.getAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase()))) {
                controllerExecutor.perform(() -> showroomService.updateShowroom(parameters));
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
