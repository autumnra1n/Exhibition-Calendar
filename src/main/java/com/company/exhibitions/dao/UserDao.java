package com.company.exhibitions.dao;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface UserDao {

    void insertUser(User user) throws DAOException, DataBaseException;
    void updateUser(User user) throws DAOException, DataBaseException;
    void deleteUserById(int id) throws DAOException, DataBaseException;
    User findAccount(User user) throws DAOException, DataBaseException;
    List<User> findAll(int limit, int offset) throws DAOException, DataBaseException;
    User findUserById(int id) throws DAOException, DataBaseException;
    List<User> findUsersByRoleId(int id, int limit, int offset) throws DAOException, DataBaseException;
    Integer getNumberOfRows() throws DAOException, DataBaseException;
    Integer getNumberOfRowsByRoleId(int id) throws DAOException, DataBaseException;
}
