package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Ticket;
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

public class MySqlTicketDao implements TicketDao {

    private final DaoExecutor<Ticket> executor = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    @Override
    public void insertTicket(Ticket ticket) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("ticket.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setTicketFieldsToStatement(ps, ticket);
            ps.executeUpdate();
        });
    }

    @Override
    public void updateTicket(Ticket ticket) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("ticket.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setTicketFieldsToStatement(ps, ticket);
            ps.setInt(4, ticket.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deleteTicketById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("ticket.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public Ticket findTicket(Ticket ticket) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Ticket detected = null;
            String sql = queryManager.getProperty("ticket.findTicket");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setTicketFieldsToStatement(ps, ticket);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                detected = processTicketRow(rs);
            }
            return detected;
        });
    }

    @Override
    public List<Ticket> findAll() throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Ticket> list = new ArrayList<>();
            String sql = queryManager.getProperty("ticket.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processTicketRow(rs));
            }
            return list;
        });
    }

    @Override
    public Ticket findTicketById(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            Ticket ticket = null;
            String sql = queryManager.getProperty("ticket.SelectTicketById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ticket = processTicketRow(rs);
            }
            return ticket;
        });
    }

    @Override
    public List<Ticket> findTicketsByExpositionId(int id) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Ticket> list = new ArrayList<>();
            String sql = queryManager.getProperty("ticket.SelectTicketsByExpositionId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processTicketRow(rs));
            }
            return list;
        });
    }

    private void setTicketFieldsToStatement(PreparedStatement ps, Ticket ticket) throws SQLException {
        ps.setString(1, ticket.getDescription());
        ps.setInt(1, ticket.getValue());;
        ps.setInt(2, ticket.getAmount());
        ps.setInt(3, ticket.getExposition().getId());
    }

    private Ticket processTicketRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("ticket.id");
        String description = rs.getString("ticket.description");
        final int value = rs.getInt("ticket.value");
        final int amount = rs.getInt("ticket.amount");
        final int expositionId = rs.getInt("ticket.exposition_id");
        String expositionTheme = rs.getString("exposition.theme");
        return new Ticket.TicketBuilder(description, value,
                new Exposition.ExpositionBuilder(expositionTheme)
                        .setId(expositionId)
                        .build())
                .setId(id)
                .setAmount(amount)
                .build();
    }
}
