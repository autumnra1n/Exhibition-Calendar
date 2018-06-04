package com.company.exhibitions.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {

    private final Connection connection;

    public ConnectionWrapper(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public Connection getConnection(){
        return connection;
    }

    public void close() throws SQLException{
        if(connection.getAutoCommit()){
               connection.close();
        }
    }
}
