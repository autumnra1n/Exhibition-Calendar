package com.company.exhibitions.dao.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.dao.utils.mysql.*;

import java.util.List;

public interface DaoExecutor<T> {

    void perform(ConnectionWrapper con, MySqlExecutable ex) throws DAOException, DataBaseException;
    T performEntitySelect(ConnectionWrapper con, MySqlEntityExecutable<T> ex) throws DAOException, DataBaseException;
    List<T> performEntityListSelect(ConnectionWrapper con, MySqlEntityListExecutable<T> ex) throws DAOException, DataBaseException;
}
