package com.company.exhibitions.web.command.expositioncommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindExpositionByIdCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IExpositionService expositionService = serviceFactory.getExpositionService();
        try{
            request.setAttribute("exposition", expositionService.findExpositionById(extractParameters(request)));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.exposition");
    }
}
