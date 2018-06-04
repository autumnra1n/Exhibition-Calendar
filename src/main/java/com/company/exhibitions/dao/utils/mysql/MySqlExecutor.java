package com.company.exhibitions.dao.utils.mysql;

import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.transaction.ConnectionWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MySqlExecutor<T> implements DaoExecutor<T> {

    @Override
    public void perform(ConnectionWrapper con, MySqlExecutable ex) throws DAOException, DataBaseException {
        try {
            ex.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }

    @Override
    public T performEntitySelect(ConnectionWrapper con, MySqlEntityExecutable<T> ex) throws DAOException, DataBaseException {
        try {
            return ex.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }

    @Override
    public List<T> performEntityListSelect(ConnectionWrapper con, MySqlEntityListExecutable<T> ex) throws DAOException, DataBaseException {
        try {
            return ex.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }
}
