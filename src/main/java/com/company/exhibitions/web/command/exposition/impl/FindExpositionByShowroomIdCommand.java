package com.company.exhibitions.web.command.exposition.impl;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.exposition.ExpositionCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FindExpositionByShowroomIdCommand extends ExpositionCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public FindExpositionByShowroomIdCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        controllerValidator = idTypeValidator.linkWith(idLengthValidator);
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IExpositionService expositionService = serviceFactory.getExpositionService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(!Objects.isNull(attribute)){
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.exposition");
        } else {
            return controllerExecutor.performAttributeSelect(() -> {
                List<Exposition> exposition = expositionService.findExpositionByShowroomId(parameters);
                return checkExpositionListPresence(request, exposition);
            });
        }
    }
}
