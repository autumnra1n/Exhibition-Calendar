package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.UserDao;
import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dto.Role;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.dao.utils.mysql.MySqlQueryManager;
import com.company.exhibitions.utils.Base64Encoder;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.utils.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    private final DaoExecutor<User> executor = new MySqlExecutor<>();
    private final DaoExecutor<Integer> rowsFounder = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = Mapper.getTransactionUtil();
    private final Base64Encoder base64Encoder = new Base64Encoder();

    @Override
    public void insertUser(User user) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("user.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setUserFieldsToStatement(ps, user);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateUser(User user) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("user.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setUserFieldsToStatement(ps, user);
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteUserById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("user.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public User findAccount(User user) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            User registered = null;
            String sql = queryManager.getProperty("user.findAccount");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                registered=processUserRow(rs);
            }
            return registered;
        });
    }

    @Override
    public List<User> findAll(int limit, int offset) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<User> list = new ArrayList<>();
            String sql = queryManager.getProperty("user.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processUserRow(rs));
            }
            return list;
        });
    }

    @Override
    public User findUserById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            User user = null;
            String sql = queryManager.getProperty("user.SelectUserById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user = processUserRow(rs);
            }
            return user;
        });
    }

    @Override
    public List<User> findUsersByRoleId(int id, int limit, int offset) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<User> list = new ArrayList<>();
            String sql = queryManager.getProperty("user.SelectUserByRoleId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processUserRow(rs));
            }
            return list;
        });
    }

    @Override
    public Integer getNumberOfRows() throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return rowsFounder.performEntitySelect(con, () -> {
            Integer rows = null;
            String sql = queryManager.getProperty("user.getNumberOfRows");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                rows = rs.getInt("total");
            }
            return rows;
        });
    }

    @Override
    public Integer getNumberOfRowsByRoleId(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return rowsFounder.performEntitySelect(con, () -> {
            Integer rows = null;
            String sql = queryManager.getProperty("user.getNumberOfRowsByRoleId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                rows = rs.getInt("total");
            }
            return rows;
        });
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
        String password = base64Encoder.decode(rs.getString("user.password"));
        String email = rs.getString("user.email");
        String firstName = rs.getString("user.firstName");
        String lastName = rs.getString("user.lastName");
        final int roleId = rs.getInt("user.role_id");
        String roleName = rs.getString("role.name");
        return new User.UserBuilder(login)
                .setId(id)
                .setPassword(password)
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(new Role.RoleBuilder(roleName)
                        .setId(roleId)
                        .build())
                .build();

    }
}
