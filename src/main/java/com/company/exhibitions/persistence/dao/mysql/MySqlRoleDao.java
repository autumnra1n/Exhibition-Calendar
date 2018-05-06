package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.MySqlExecutable;
import com.company.exhibitions.dao.utils.MySqlExecutor;
import com.company.exhibitions.querymanager.MySqlQueryManager;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDao implements RoleDao {

    @Override
    public void insertRole(Role role) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("role.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setRoleFieldsToStatement(ps, role);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateRole(Role role) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("role.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setRoleFieldsToStatement(ps, role);
            ps.setInt(2, role.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteRoleById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("role.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<Role> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<Role> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("role.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRoleRow(rs));
            }
        });
        return list;
    }

    @Override
    public Role findRoleById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        class RoleExecutable implements MySqlExecutable {
            private Role role;

            @Override
            public void exec() throws SQLException {
                String sql = MySqlQueryManager.getProperty("role.SelectRoleById");
                PreparedStatement ps = con.createPreparedStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                role = processRoleRow(rs);
            }
        }
        RoleExecutable roleDaoExecutable = new RoleExecutable();
        MySqlExecutor.execute(con, roleDaoExecutable);
        return roleDaoExecutable.role;
    }

    private void setRoleFieldsToStatement(PreparedStatement ps, Role role) throws SQLException {
        ps.setString(1, role.getName());
    }

    private Role processRoleRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        String name = rs.getString("name");
        return new Role(id, name);
    }
}
