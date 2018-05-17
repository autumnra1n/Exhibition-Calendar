package com.company.exhibitions.connectionpool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionsPool {

    private static ConnectionsPool pool;
    private final DataSource ds;

    private ConnectionsPool() throws NamingException{
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup("java:comp/env");
        ds = (DataSource) environmentContext.lookup("jdbc/exhibitions");
    }

    public static synchronized ConnectionsPool getInstance() throws NamingException{
        if(Objects.isNull(pool)){
            pool = new ConnectionsPool();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
