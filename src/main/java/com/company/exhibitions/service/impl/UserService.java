package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.UserDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<User> executor = new ServiceExecutor<>();

    @Override
    public void insertUser(Map<String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(() -> userDao.insertUser(buildUser(parameters)));
    }

    @Override
    public void updateUser(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(() -> userDao.updateUser(buildUserWithId(parameters)));
    }

    @Override
    public void deleteUser(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(() -> userDao.deleteUserById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public User findAccount(Map <String, String> parameters) throws ServiceException{
        UserDao userDao = factory.getUserDao();
        return executor.performEntitySelect(() -> userDao.findAccount(buildLogin(parameters)));
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntityListSelect(userDao::findAll);
    }

    @Override
    public User findUserById(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntitySelect(() -> userDao.findUserById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public List<User> findUsersByRoleId(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntityListSelect(() -> userDao.findUsersByRoleId(Integer.valueOf(parameters.get("id"))));
    }

    private User buildLogin(Map <String, String> parameters){
        return new User.UserBuilder(parameters.get("login")).setPassword(parameters.get("password")).build();
    }

    private User buildUser(Map <String, String> parameters){
        return new User.UserBuilder(parameters.get("login"))
                .setEmail(parameters.get("email"))
                .setFirstName(parameters.get("firstName"))
                .setLastName(parameters.get("lastName"))
                .setPassword(parameters.get("password"))
                .setRole(new Role.RoleBuilder(parameters.get("roleName"))
                        .setId(Integer.valueOf(parameters.get("roleId")))
                        .build())
                .build();
    }

    private User buildUserWithId(Map <String, String> parameters){
        return new User.UserBuilder(parameters.get("login"))
                .setEmail(parameters.get("email"))
                .setFirstName(parameters.get("firstName"))
                .setLastName(parameters.get("lastName"))
                .setPassword(parameters.get("password"))
                .setRole(new Role.RoleBuilder(parameters.get("roleName"))
                        .setId(Integer.valueOf(parameters.get("roleId")))
                        .build())
                .setId(Integer.valueOf(parameters.get("id")))
                .build();
    }
}
