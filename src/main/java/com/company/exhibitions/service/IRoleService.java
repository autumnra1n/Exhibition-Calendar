package com.company.exhibitions.service;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IRoleService {

    void insertRole(Map<String, String> parameters) throws ServiceException;
    void updateRole(Map <String, String> parameters) throws ServiceException;
    void deleteRole(Map <String, String> parameters) throws ServiceException;
    Role findRole(Map <String, String> parameters) throws ServiceException;
    List<Role> findAll() throws ServiceException;
    Role findRoleById (Map <String, String> parameters) throws ServiceException;
}
