package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleService implements IRoleService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Role> executor = new ServiceExecutor<>();

    @Override
    public void insertRole(Map<String, String> parameters) throws ServiceException {
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(() -> roleDao.insertRole(buildRole(parameters)));
    }

    @Override
    public void updateRole(Map <String, String> parameters) throws ServiceException {
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(() -> roleDao.updateRole(buildRoleWithId(parameters)));
    }

    @Override
    public void deleteRole(Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(() -> roleDao.deleteRoleById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public Role findRole(Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntitySelect(() -> roleDao.findRole(buildRole(parameters)));
    }

    @Override
    public List<Role> findAll() throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntityListSelect(roleDao::findAll);
    }

    @Override
    public Role findRoleById (Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntitySelect(() -> roleDao.findRoleById(Integer.valueOf(parameters.get("id"))));
    }

    private Role buildRole(Map <String, String> parameters){
        return new Role.RoleBuilder(parameters.get("name")).build();
    }

    private Role buildRoleWithId(Map <String, String> parameters){
        return new Role.RoleBuilder(parameters.get("name"))
                .setId(Integer.valueOf(parameters.get("id")))
                .build();
    }
}
