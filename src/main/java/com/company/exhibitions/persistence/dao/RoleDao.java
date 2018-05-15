package com.company.exhibitions.dao;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface RoleDao {

    void insertRole(Role role) throws DAOException, DataBaseException;
    void updateRole(Role role) throws DAOException, DataBaseException;
    void deleteRoleById(int id) throws DAOException, DataBaseException;
    Role findRole(Role role) throws DAOException, DataBaseException;
    List<Role> findAll() throws DAOException, DataBaseException;
    Role findRoleById(int id) throws DAOException, DataBaseException;
}
