package com.company.exhibitions.service.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

public interface ServiceEntityExecutable<T> {

    T execute() throws DataBaseException, DAOException;
}
