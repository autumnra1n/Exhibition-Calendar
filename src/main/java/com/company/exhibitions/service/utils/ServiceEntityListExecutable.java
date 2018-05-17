package com.company.exhibitions.service.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface ServiceEntityListExecutable<T> {

    List<T> execute() throws DataBaseException, DAOException;
}
