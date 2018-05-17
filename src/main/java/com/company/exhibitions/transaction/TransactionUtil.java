package com.company.exhibitions.transaction;

import com.company.exhibitions.connectionpool.ConnectionsPool;
import com.company.exhibitions.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionUtil {

    private static final ThreadLocal<ConnectionWrapper> connections  = new ThreadLocal<>();
    private static final TransactionUtil transactionUtil = new TransactionUtil();

    private TransactionUtil(){}

    public void beginTransaction() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if(Objects.nonNull(con)){
            throw new DataBaseException();
        }
        try {
            Connection connection = ConnectionsPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            con = new ConnectionWrapper(connection);
            connections.set(con);
        }catch(NamingException | SQLException e){
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
            Connection connection = ConnectionsPool.getInstance().getConnection();
            connection.setAutoCommit(true);
            con = new ConnectionWrapper(connection);
            return con;
        }catch(NamingException | SQLException e){
                throw new DataBaseException(e);
        }
    }

    public static TransactionUtil getInstance(){
        return transactionUtil;
    }
}
