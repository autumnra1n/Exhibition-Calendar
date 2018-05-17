package com.company.exhibitions.web.utils;

import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerExecutor {

    private final static Logger LOGGER = LogManager.getLogger(ControllerExecutor.class);

    public void perform(ControllerExecutable controllerExecutable){
        try {
            controllerExecutable.execute();
        } catch (ServiceException e) {
            LOGGER.error("Service exception occurred in" + MySqlExecutor.class.getName(), e);
        }
    }

    public String performAttributeSelect(AttributeExecutable attributeExecutable){
        try {
            return attributeExecutable.execute();
        } catch (ServiceException e) {
            LOGGER.error("Service exception occurred in" + MySqlExecutor.class.getName(), e);
            return null;
        }
    }
}
