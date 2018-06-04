package com.company.exhibitions.service.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

public interface ServiceExecutable {

    void execute() throws DataBaseException, DAOException;
}
