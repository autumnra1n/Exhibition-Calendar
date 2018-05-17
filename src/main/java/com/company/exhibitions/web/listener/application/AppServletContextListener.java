package com.company.exhibitions.web.listener.application;

import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.controller.impl.ControllerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Mapper.setServletContext(servletContext);
        servletContext.setAttribute("mySqlDaoFactory", new MySqlDaoFactory());
        servletContext.setAttribute("serviceFactory", new ServiceFactory());
        servletContext.setAttribute("controllerFactory", new ControllerFactory());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
