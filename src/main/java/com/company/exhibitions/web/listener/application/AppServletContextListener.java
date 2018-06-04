package com.company.exhibitions.web.listener.application;

import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.controller.impl.ControllerFactory;
import com.company.exhibitions.web.controller.validation.application.CredentialsValidator;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Mapper.setServletContext(servletContext);
        servletContext.setAttribute("transactionUtil", new TransactionUtil());
        servletContext.setAttribute("controllerExecutor", new ControllerExecutor());
        servletContext.setAttribute("pageManager", new PageManager());
        servletContext.setAttribute("paginationUtil", new PaginationUtil());
        servletContext.setAttribute("mySqlDaoFactory", new MySqlDaoFactory());
        servletContext.setAttribute("serviceFactory", new ServiceFactory());
        servletContext.setAttribute("controllerFactory", new ControllerFactory());
        servletContext.setAttribute("adminCredentialsValidator", new CredentialsValidator());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
