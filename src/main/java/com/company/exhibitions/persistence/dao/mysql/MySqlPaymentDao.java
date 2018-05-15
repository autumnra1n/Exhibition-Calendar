package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.PaymentDao;
import com.company.exhibitions.dao.utils.DaoExecutor;
import com.company.exhibitions.dao.utils.QueryManager;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.dao.utils.mysql.MySqlExecutor;
import com.company.exhibitions.dao.utils.mysql.MySqlQueryManager;
import com.company.exhibitions.transaction.ConnectionWrapper;
import com.company.exhibitions.transaction.TransactionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPaymentDao implements PaymentDao {

    private final DaoExecutor<Payment> executor = new MySqlExecutor<>();
    private final QueryManager queryManager = new MySqlQueryManager();
    private final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    @Override
    public void insertPayment(Payment payment) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("payment.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setPaymentFieldsToStatement(ps, payment);
            ps.executeUpdate();
        });
    }

    @Override
    public void updatePayment(Payment payment) throws DAOException, DataBaseException {
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("payment.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setPaymentFieldsToStatement(ps, payment);
            ps.setInt(4, payment.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deletePaymentById(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        executor.perform(con, () -> {
            String sql = queryManager.getProperty("payment.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public Payment findPayment(Payment payment) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            String sql = queryManager.getProperty("payment.findPayment");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1,payment.getId());
            ps.setInt(2, payment.getTicket().getId());
            ResultSet rs = ps.executeQuery();
            return processPaymentRow(rs);
        });
    }

    @Override
    public List<Payment> findAll() throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Payment> list = new ArrayList<>();
            String sql = queryManager.getProperty("payment.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(processPaymentRow(rs));
            }
            return list;
        });
    }

    @Override
    public Payment findPaymentById(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            String sql = queryManager.getProperty("payment.SelectPaymentById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            return processPaymentRow(rs);
        });
    }

    @Override
    public List<Payment> findPaymentsByUserId(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntityListSelect(con, () -> {
            List<Payment> list = new ArrayList<>();
            String sql = queryManager.getProperty("payment.SelectPaymentsByUserId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(processPaymentRow(rs));
            }
            return list;
        });
    }

    @Override
    public Payment findPaymentByTicketId(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = transactionUtil.getConnection();
        return executor.performEntitySelect(con, () -> {
            String sql = queryManager.getProperty("payment.SelectPaymentByTicketId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            return processPaymentRow(rs);
        });
    }

    private void setPaymentFieldsToStatement(PreparedStatement ps, Payment payment) throws SQLException {
        ps.setInt(1, payment.getUser().getId());
        ps.setInt(2, payment.getTicket().getId());
        ps.setDate(3, payment.getDate());
    }

    private Payment processPaymentRow(ResultSet rs) throws SQLException {
        final int id = rs.getInt("payment.id");
        final Date date = rs.getDate("payment.date");
        final int userId = rs.getInt("payment.user_id");
        String userLogin = rs.getString("user.login");
        final int ticketId = rs.getInt("payment.ticket_id");
        String ticketDescription = rs.getString("ticket.description");
        final int ticketValue = rs.getInt("ticket.value");
        final int expositionId = rs.getInt("ticket.exposition_id");
        String expositionTheme = rs.getString("exposition.theme");
        return new Payment.PaymentBuilder(date,new User.UserBuilder(userLogin)
                .setId(userId)
                .build(),
                new Ticket.TicketBuilder(ticketDescription, ticketValue, new Exposition.ExpositionBuilder(expositionTheme)
                        .setId(expositionId)
                        .build())
                        .setId(ticketId)
                        .build())
                .setId(id)
                .build();
    }
}
