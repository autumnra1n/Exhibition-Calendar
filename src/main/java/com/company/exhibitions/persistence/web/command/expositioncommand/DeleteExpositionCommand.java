package com.company.exhibitions.web.command.expositioncommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DeleteExpositionCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IExpositionService expositionService = serviceFactory.getExpositionService();
        Map<String, String> parameters = extractParameters(request);
        try {
            expositionService.deleteExposition(parameters);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.admin");
    }
}
