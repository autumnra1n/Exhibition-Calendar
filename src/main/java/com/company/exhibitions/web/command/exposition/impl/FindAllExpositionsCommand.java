package com.company.exhibitions.web.command.exposition.impl;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.exposition.ExpositionCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllExpositionsCommand extends ExpositionCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllExpositionsCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IExpositionService expositionService = serviceFactory.getExpositionService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<Exposition> expositions = expositionService.findAll();
            return checkExpositionListPresence(request, expositions);
        });
    }
}
