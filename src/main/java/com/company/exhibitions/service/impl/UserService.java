package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.UserDao;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.utils.*;
import com.company.exhibitions.transaction.Transactional;
import com.company.exhibitions.utils.Base64Encoder;
import com.company.exhibitions.utils.Mapper;

import java.util.List;
import java.util.Map;

public class UserService implements IUserService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<User> executor = new ServiceExecutor<>();
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();
    private final Base64Encoder base64Encoder = new Base64Encoder();

    @Override
    public void insertUser(Map<String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                userDao.insertUser(buildUser(parameters));
            }
        });
    }

    @Override
    public void updateUser(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                User user = buildUser(parameters);
                System.out.println(user.toString());
                System.out.println(user.toString());
                System.out.println(user.toString());
                userDao.updateUser(buildUser(parameters));
            }
        });
    }

    @Override
    public void deleteUser(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                userDao.deleteUserById(Integer.valueOf(parameters.get("userId")));
            }
        });
    }

    @Override
    public User findAccount(Map <String, String> parameters) throws ServiceException{
        UserDao userDao = factory.getUserDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<User>() {
            @Override
            public User execute() throws DataBaseException, DAOException {
                return userDao.findAccount(buildLogin(parameters));            }
        });
    }

    @Override
    public List<User> findAll(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<User>() {
            @Override
            public List<User> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return userDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public User findUserById(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<User>() {
            @Override
            public User execute() throws DataBaseException, DAOException {
                return userDao.findUserById(Integer.valueOf(parameters.get("userId")));
            }
        });
    }

    @Override
    public List<User> findUsersByRoleId(Map <String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<User>() {
            @Override
            public List<User> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return userDao.findUsersByRoleId(Integer.valueOf(parameters.get("roleId")), limit, offset);
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return userDao.getNumberOfRows();
            }
        });
    }

    @Override
    public Integer getNumberOfRowsByRoleId(Map<String, String> parameters) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return userDao.getNumberOfRowsByRoleId(Integer.valueOf(parameters.get("roleId")));
            }
        });
    }

    private User buildLogin(Map <String, String> parameters){
        return new User.UserBuilder(parameters.get("login")).setPassword(base64Encoder.encode(parameters.get("password"))).build();
    }

    private User buildUser(Map <String, String> parameters){
        return new User.UserBuilder(parameters.get("login"))
                .setEmail(parameters.get("email"))
                .setFirstName(parameters.get("firstName"))
                .setLastName(parameters.get("lastName"))
                .setPassword(base64Encoder.encode(parameters.get("password")))
                .setId(Integer.valueOf(parameters.get("userId")))
                .setRole(new Role.RoleBuilder(parameters.get("roleName"))
                        .setId(Integer.valueOf(parameters.get("roleId")))
                        .build())
                .build();
    }
}
