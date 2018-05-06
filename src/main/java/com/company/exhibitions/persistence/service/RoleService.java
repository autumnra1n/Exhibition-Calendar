package com.company.exhibitions.service;

import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.transaction.TransactionUtil;

import java.util.List;

public class RoleService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertRole(Role role) throws ServiceException {
        try {
            RoleDao roleDao = factory.getRoleDao();
            try {
                roleDao.insertRole(role);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updateRole(Role role) throws ServiceException {
        try {
            RoleDao roleDao = factory.getRoleDao();
            try {
                roleDao.updateRole(role);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteRole(int id) throws ServiceException{
        try {
            RoleDao roleDao = factory.getRoleDao();
            try {
                roleDao.deleteRoleById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<Role> findAll() throws ServiceException{
        try {
            RoleDao roleDao = factory.getRoleDao();
            try {
                return roleDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Role findRoleById (int id) throws ServiceException{
        try {
            RoleDao roleDao = factory.getRoleDao();
            try {
                return roleDao.findRoleById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

}
