package com.company.exhibitions.service;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IUserService {

    void insertUser(Map<String, String> parameters) throws ServiceException;
    void updateUser(Map <String, String> parameters) throws ServiceException;
    void deleteUser(Map <String, String> parameters) throws ServiceException;
    User findAccount(Map <String, String> parameters) throws ServiceException;
    List<User> findAll() throws ServiceException;
    User findUserById(Map <String, String> parameters) throws ServiceException;
    List<User> findUsersByRoleId(Map <String, String> parameters) throws ServiceException;
}
