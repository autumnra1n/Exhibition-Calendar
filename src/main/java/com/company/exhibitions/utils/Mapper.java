package com.company.exhibitions.utils;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.web.controller.impl.ControllerFactory;

import javax.servlet.ServletContext;

public class Mapper {

    private static ServletContext servletContext;

    public static void setServletContext(ServletContext context){
        servletContext = context;
    }

    public static DaoFactory getDaoFactory(){
        return (DaoFactory) servletContext.getAttribute("mySqlDaoFactory");
    }

    public static ServiceFactory getServiceFactory(){
        return (ServiceFactory) servletContext.getAttribute("serviceFactory");
    }

    public static ControllerFactory getControllerFactory(){
        return (ControllerFactory) servletContext.getAttribute("controllerFactory");
    }
}
