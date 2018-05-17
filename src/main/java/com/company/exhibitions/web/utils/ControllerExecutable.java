package com.company.exhibitions.web.utils;

import com.company.exhibitions.exception.ServiceException;

public interface ControllerExecutable {

    void execute() throws ServiceException;
}
