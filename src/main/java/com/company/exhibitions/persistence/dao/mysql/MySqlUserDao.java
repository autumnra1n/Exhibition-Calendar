package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.UserDao;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.MySqlExecutable;
import com.company.exhibitions.dao.utils.MySqlExecutor;
import com.company.exhibitions.querymanager.MySqlQueryManager;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    @Override
    public void insertUser(User user) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("user.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setUserFieldsToStatement(ps, user);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateUser(User user) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("user.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setUserFieldsToStatement(ps, user);
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteUserById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("user.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<User> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<User> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("user.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processUserRow(rs));
            }
        });
        return list;
    }

    @Override
    public User findUserById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        class UserExecutable implements MySqlExecutable {
            private User user;

            @Override
            public void exec() throws SQLException {
                String sql = MySqlQueryManager.getProperty("user.SelectUserById");
                PreparedStatement ps = con.createPreparedStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                user = processUserRow(rs);
            }
        }
        UserExecutable userDaoExecutable = new UserExecutable();
        MySqlExecutor.execute(con, userDaoExecutable);
        return userDaoExecutable.user;
    }

    @Override
    public List<User> findUsersByRoleId(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<User> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("user.SelectUserByRoleId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processUserRow(rs));
            }
        });
        return list;
    }

    private void setUserFieldsToStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getFirstName());
        ps.setString(5, user.getLastName());
        ps.setInt(6, user.getRole().getId());
    }

    private User processUserRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("user.id");
        String login = rs.getString("user.login");
        String password = rs.getString("user.password");
        String email = rs.getString("user.email");
        String firstName = rs.getString("user.firstName");
        String lastName = rs.getString("user.lastName");
        final int roleId = rs.getInt("user.role_id");
        String roleName = rs.getString("role.name");
        return new User.UserBuilder(id, login)
                .setPassword(password)
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(new Role(roleId, roleName))
                .build();
    }
}
