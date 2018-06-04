package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.ExpositionDao;
import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.dao.utils.mysql.MySqlQueryManager;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.utils.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpositionDao implements ExpositionDao {

    private final DaoExecutor<Exposition> executor = new MySqlExecutor<>();
    private final DaoExecutor<Integer> rowsFounder = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = Mapper.getTransactionUtil();

    @Override
    public void insertExposition(Exposition exposition) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("exposition.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setExpositionFieldsToStatement(ps, exposition);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateExposition(Exposition exposition) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("exposition.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setExpositionFieldsToStatement(ps, exposition);
            ps.setInt(6, exposition.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteExpositionById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("exposition.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<Exposition> findAll(int limit, int offset) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Exposition> list = new ArrayList<>();
            String sql = queryManager.getProperty("exposition.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processExpositionRow(rs));
            }
            return list;
        });
    }

    @Override
    public Exposition findExposition(Exposition exposition) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Exposition detected = null;
            String sql = queryManager.getProperty("exposition.findExposition");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setExpositionFieldsToStatement(ps, exposition);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                detected = processExpositionRow(rs);
            }
            return detected;
        });
    }

    @Override
    public Exposition findExpositionById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Exposition exposition = null;
            String sql = queryManager.getProperty("exposition.SelectExpositionById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                exposition = processExpositionRow(rs);
            }
            return exposition;
        });
    }

    @Override
    public List<Exposition> findExpositionsByShowroomId(int id, int limit, int offset) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Exposition> list = new ArrayList<>();
            String sql = queryManager.getProperty("exposition.SelectExpositionsByShowroomId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processExpositionRow(rs));
            }
            return list;
        });
    }

    @Override
    public Integer getNumberOfRows() throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return rowsFounder.performEntitySelect(con, () -> {
            Integer rows = null;
            String sql = queryManager.getProperty("exposition.getNumberOfRows");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                rows = rs.getInt("total");
            }
            return rows;
        });
    }

    @Override
    public Integer getNumberOfRowsByShowroomId(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return rowsFounder.performEntitySelect(con, () -> {
            Integer rows = null;
            String sql = queryManager.getProperty("exposition.getNumberOfRowsByShowroomId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                rows = rs.getInt("total");
            }
            return rows;
        });
    }

    private void setExpositionFieldsToStatement(PreparedStatement ps, Exposition exposition) throws SQLException {
        ps.setString(1, exposition.getTheme());
        ps.setDate(2, exposition.getDateStart());
        ps.setTime(3, exposition.getStartTime());
        ps.setString(4, exposition.getDescription());
        ps.setInt(5, exposition.getShowroom().getId());
    }

    private Exposition processExpositionRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("exposition.id");
        String theme = rs.getString("exposition.theme");
        final Date dateStart = rs.getDate("exposition.dateStart");
        final Time startTime = rs.getTime("exposition.startTime");
        String description = rs.getString("exposition.description");
        final int showroomId = rs.getInt("showroom.id");
        String showroomName = rs.getString("showroom.name");
        return new Exposition.ExpositionBuilder(theme)
                .setId(id)
                .setDateStart(dateStart)
                .setStartTime(startTime)
                .setDescription(description)
                .setShowroom(new Showroom.ShowroomBuilder(showroomName)
                        .setId(showroomId)
                        .build())
                .build();
    }
}

