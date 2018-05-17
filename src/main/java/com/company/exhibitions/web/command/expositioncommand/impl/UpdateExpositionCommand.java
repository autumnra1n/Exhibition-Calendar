package com.company.exhibitions.web.command.expositioncommand.impl;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.expositioncommand.ExpositionCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.controller.validation.factories.ExpositionParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class UpdateExpositionCommand extends ExpositionCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public UpdateExpositionCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ExpositionParametersValidatorFactory expositionParametersValidatorFactory = new ExpositionParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        ControllerValidator dateValidator = commonParametersValidatorFactory.getDateValidator();
        ControllerValidator timeValidator = commonParametersValidatorFactory.getTimeValidator();
        ControllerValidator descriptionValidator = commonParametersValidatorFactory.getDescriptionValidator();
        ControllerValidator themeLengthValidator = expositionParametersValidatorFactory.getThemeLengthValidator();
        this.controllerExecutor = new ControllerExecutor();
        this.controllerValidator = idTypeValidator
                .linkWith(idLengthValidator)
                .linkWith(dateValidator)
                .linkWith(timeValidator)
                .linkWith(descriptionValidator)
                .linkWith(themeLengthValidator);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IExpositionService expositionService = serviceFactory.getExpositionService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() -> {
                Exposition exposition = expositionService.findExposition(parameters);
                checkExpositionPresence(request, exposition);
            });
            if (!Objects.isNull(request.getAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase()))) {
                controllerExecutor.perform(() -> {
                    expositionService.updateExposition(parameters);
                });
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
