package com.company.exhibitions.service;

import com.company.exhibitions.dao.UserDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;

public class UserService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertUser(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            try {
                userDao.insertUser(user);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            try {
                userDao.updateUser(user);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteUser(int id) throws ServiceException{
        try {
            UserDao userDao = factory.getUserDao();
            try {
                userDao.deleteUserById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> findAll() throws ServiceException{
        try {
            UserDao userDao = factory.getUserDao();
            try {
                return userDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public User findUserById (int id) throws ServiceException{
        try {
            UserDao userDao = factory.getUserDao();
            try {
                return userDao.findUserById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public List<User> findUsersByRoleId(int id) throws ServiceException{
        try {
            UserDao userDao = factory.getUserDao();
            try {
                return userDao.findUsersByRoleId(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
