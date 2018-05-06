package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.ShowroomDao;
import com.company.exhibitions.dto.Showroom;
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

public class MySqlShowroomDao implements ShowroomDao {

    @Override
    public void insertShowroom(Showroom showroom) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("showroom.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setShowroomFieldsToStatement(ps, showroom);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateShowroom(Showroom showroom) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("showroom.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setShowroomFieldsToStatement(ps, showroom);
            ps.setInt(4, showroom.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteShowroomById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("showroom.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<Showroom> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<Showroom> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("showroom.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processShowroomRow(rs));
            }
        });
        return list;
    }

    @Override
    public Showroom findShowroomById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        class ShowroomExecutable implements MySqlExecutable {
            private Showroom showroom;

            @Override
            public void exec() throws SQLException {
                String sql = MySqlQueryManager.getProperty("showroom.SelectShowroomById");
                PreparedStatement ps = con.createPreparedStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                showroom = processShowroomRow(rs);
            }
        }
        ShowroomExecutable showroomDaoExecutable = new ShowroomExecutable();
        MySqlExecutor.execute(con, showroomDaoExecutable);
        return showroomDaoExecutable.showroom;
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
        return new Showroom.ShowroomBuilder(id,name)
                .setLocation(location)
                .setDescription(description)
                .build();
    }
}
