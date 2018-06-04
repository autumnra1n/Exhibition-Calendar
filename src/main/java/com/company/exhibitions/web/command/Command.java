package com.company.exhibitions.web.command;

import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {

    private final PageManager pageManager;
    private final ControllerExecutor controllerExecutor;
    private final ServiceFactory serviceFactory;
    private final PaginationUtil paginationUtil;

    public Command(){
        this.pageManager = Mapper.getPageManager();
        this.controllerExecutor = Mapper.getControllerExecutor();
        this.serviceFactory = Mapper.getServiceFactory();
        this.paginationUtil = Mapper.getPaginationUtil();
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    public Map<String, String> extractParameters(HttpServletRequest request){
        Map<String, String> requestParameters = new HashMap<>();

        List<String> parameters = Collections.list(request.getParameterNames());

        for (String param : parameters) {
            requestParameters.put(param, request.getParameter(param));
        }

        return requestParameters;
    }

    public PageManager getPageManager() {
        return pageManager;
    }

    public ControllerExecutor getControllerExecutor() {
        return controllerExecutor;
    }

    public ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public PaginationUtil getPaginationUtil() {
        return paginationUtil;
    }
}
