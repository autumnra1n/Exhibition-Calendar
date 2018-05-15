package com.company.exhibitions.web.listener.application;

import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.service.impl.ServiceFactory;
//import com.company.exhibitions.service.validation.ServiceValidator;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.controller.impl.ControllerFactory;
import com.company.exhibitions.web.controller.validation.ControllerValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("mySqlDaoFactory", new MySqlDaoFactory());
        servletContext.setAttribute("serviceFactory", new ServiceFactory());
        servletContext.setAttribute("controllerFactory", new ControllerFactory());
//        servletContext.setAttribute("serviceValidator", new ServiceValidator());
        servletContext.setAttribute("controllerValidator", new ControllerValidator());
        Mapper.setServletContext(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
