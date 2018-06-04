package com.company.exhibitions.transaction;

import com.company.exhibitions.connectionpool.ConnectionsPool;
import com.company.exhibitions.exception.DataBaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionUtil {

    private static final ThreadLocal<ConnectionWrapper> connections = new ThreadLocal<>();
    private final ConnectionsPool connectionsPool;

    public TransactionUtil() {
        this.connectionsPool = new ConnectionsPool();
    }

    public void beginTransaction() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if(Objects.nonNull(con)){
            throw new DataBaseException();
        }
        try {
            Connection connection = connectionsPool.getConnection();
            connection.setAutoCommit(false);
            con = new ConnectionWrapper(connection);
            connections.set(con);
        }catch(SQLException e){
            throw new DataBaseException(e);
        }
    }

    public void endTransaction() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if(Objects.isNull(con)){
            throw new DataBaseException();
        }
        try {
            con.getConnection().close();
        }catch(SQLException e){
           throw new DataBaseException(e);
        }
        connections.set(null);
    }

    public void rollback() throws DataBaseException{
        ConnectionWrapper con = connections.get();
        if(Objects.isNull(con)){
            throw new DataBaseException();
        }
        try{
           con.getConnection().rollback();
        }catch(SQLException e){
            throw new DataBaseException(e);
        }
    }
    
    public void commit() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if(Objects.isNull(con)){
            throw new DataBaseException();
        }
        try{
            con.getConnection().commit();
        }catch(SQLException e){
            throw new DataBaseException(e);
        }
    }
    public ConnectionWrapper getConnection() throws DataBaseException{
        ConnectionWrapper con = connections.get();
        if (Objects.nonNull(con)) {
            return con;
        }
        try {
            Connection connection = connectionsPool.getConnection();
            connection.setAutoCommit(true);
            con = new ConnectionWrapper(connection);
            return con;
        }catch(SQLException e){
                throw new DataBaseException(e);
        }
    }
}
