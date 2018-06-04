package com.company.exhibitions.utils;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.web.controller.impl.ControllerFactory;
import com.company.exhibitions.web.controller.validation.application.CredentialsValidator;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;
import com.company.exhibitions.web.utils.PaginationUtil;

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

    public static PageManager getPageManager(){
        return (PageManager) servletContext.getAttribute("pageManager");
    }

    public static TransactionUtil getTransactionUtil() {return (TransactionUtil) servletContext.getAttribute("transactionUtil");}

    public static CredentialsValidator getAdminCredentialsValidator() {return (CredentialsValidator) servletContext.getAttribute("adminCredentialsValidator");}

    public static PaginationUtil getPaginationUtil() {return (PaginationUtil) servletContext.getAttribute("paginationUtil");}

    public static ControllerExecutor getControllerExecutor() {return (ControllerExecutor) servletContext.getAttribute("controllerExecutor");}
}
