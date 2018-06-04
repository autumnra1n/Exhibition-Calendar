package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.PaymentDao;
import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IRoleService;
import com.company.exhibitions.service.utils.ServiceEntityExecutable;
import com.company.exhibitions.service.utils.ServiceEntityListExecutable;
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
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();

    @Override
    public void insertRole(Map<String, String> parameters) throws ServiceException {
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                roleDao.insertRole(buildRole(parameters));
            }
        });
    }

    @Override
    public void updateRole(Map <String, String> parameters) throws ServiceException {
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                roleDao.updateRole(buildRoleWithId(parameters));
            }
        });
    }

    @Override
    public void deleteRole(Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                roleDao.deleteRoleById(Integer.valueOf(parameters.get("roleId")));
            }
        });
    }

    @Override
    public Role findRole(Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Role>() {
            @Override
            public Role execute() throws DataBaseException, DAOException {
                return roleDao.findRole(buildRole(parameters));
            }
        });
    }

    @Override
    public List<Role> findAll(Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Role>() {
            @Override
            public List<Role> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return roleDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public Role findRoleById (Map <String, String> parameters) throws ServiceException{
        RoleDao roleDao = factory.getRoleDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Role>() {
            @Override
            public Role execute() throws DataBaseException, DAOException {
                return roleDao.findRoleById(Integer.valueOf(parameters.get("roleId")));
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        RoleDao roleDao = factory.getRoleDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return roleDao.getNumberOfRows();
            }
        });
    }

    private Role buildRole(Map <String, String> parameters){
        return new Role.RoleBuilder(parameters.get("roleName")).build();
    }

    private Role buildRoleWithId(Map <String, String> parameters){
        return new Role.RoleBuilder(parameters.get("roleName"))
                .setId(Integer.valueOf(parameters.get("roleId")))
                .build();
    }
}
