package com.company.exhibitions.dao.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.transaction.ConnectionWrapper;

import java.sql.SQLException;

public class MySqlExecutor {
        public static void execute(ConnectionWrapper con, MySqlExecutable ex) throws DAOException, DataBaseException {
        try {
            ex.exec();
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
