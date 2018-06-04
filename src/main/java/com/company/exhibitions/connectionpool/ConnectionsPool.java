package com.company.exhibitions.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionsPool {

    private DataSource ds;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionsPool.class);

    public ConnectionsPool(){
        try {
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            ds = (DataSource) environmentContext.lookup("jdbc/exhibitions");
        } catch (NamingException e){
            LOGGER.error("NamingException occurred in "+ConnectionsPool.class.getName(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
