package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.ShowroomDao;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.dao.utils.mysql.MySqlQueryManager;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlShowroomDao implements ShowroomDao {

    private final MySqlExecutor<Showroom> executor = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    @Override
    public void insertShowroom(Showroom showroom) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("showroom.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setShowroomFieldsToStatement(ps, showroom);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateShowroom(Showroom showroom) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("showroom.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setShowroomFieldsToStatement(ps, showroom);
            ps.setInt(4, showroom.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteShowroomById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("showroom.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<Showroom> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Showroom> list = new ArrayList<>();
            String sql = queryManager.getProperty("showroom.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processShowroomRow(rs));
            }
            return list;
        });
    }

    @Override
    public Showroom findShowroom(Showroom showroom) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Showroom detected = null;
            String sql = queryManager.getProperty("showroom.findShowroom");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setString(1, showroom.getName());
            ps.setString(2, showroom.getLocation());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                detected = processShowroomRow(rs);
            }
            return detected;
        });
    }

    @Override
    public Showroom findShowroomById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Showroom showroom = null;
            String sql = queryManager.getProperty("showroom.SelectShowroomById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                showroom = processShowroomRow(rs);
            }
            return showroom;
        });
    }

    private void setShowroomFieldsToStatement(PreparedStatement ps, Showroom showroom) throws SQLException {
        ps.setString(1, showroom.getName());
        ps.setString(2, showroom.getLocation());
        ps.setString(3, showroom.getDescription());
    }

    private Showroom processShowroomRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        String name = rs.getString("name");
        String location = rs.getString("location");
        String description = rs.getString("description");
        return new Showroom.ShowroomBuilder(name)
                .setId(id)
                .setLocation(location)
                .setDescription(description)
                .build();
    }
}
