package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.dao.utils.mysql.MySqlQueryManager;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDao implements RoleDao {

    private final DaoExecutor<Role> executor = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    @Override
    public void insertRole(Role role) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("role.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setRoleFieldsToStatement(ps, role);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateRole(Role role) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("role.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setRoleFieldsToStatement(ps, role);
            ps.setInt(2, role.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteRoleById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("role.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public Role findRole(Role role) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Role detected = null;
            String sql = queryManager.getProperty("role.findRole");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setString(1, role.getName());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                detected = processRoleRow(rs);
            }
            return detected;
        });
    }

    @Override
    public List<Role> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Role> list = new ArrayList<>();
            String sql = queryManager.getProperty("role.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRoleRow(rs));
            }
            return list;
        });
    }

    @Override
    public Role findRoleById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Role role = null;
            String sql = queryManager.getProperty("role.SelectRoleById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                role = processRoleRow(rs);
            }
            return role;
        });
    }

    private void setRoleFieldsToStatement(PreparedStatement ps, Role role) throws SQLException {
        ps.setString(1, role.getName());
    }

    private Role processRoleRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        String name = rs.getString("name");
        return new Role.RoleBuilder(name)
                .setId(id)
                .build();
    }
}
