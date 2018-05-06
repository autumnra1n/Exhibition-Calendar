package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.PaymentDao;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
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

public class MySqlPaymentDao implements PaymentDao {

    @Override
    public void insertPayment(Payment payment) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("payment.Insert");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setPaymentFieldsToStatement(ps, payment);
            ps.executeUpdate();
        });
    }

    @Override
    public void updatePayment(Payment payment) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("payment.Update");
            PreparedStatement ps = con.createPreparedStatement(sql);
            setPaymentFieldsToStatement(ps, payment);
            ps.setInt(4, payment.getId());
            ps.executeUpdate();
        });
    }

    @Override
    public void deletePaymentById(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = TransactionUtil.getConnection();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("payment.DeleteById");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        });
    }

    @Override
    public List<Payment> findAll() throws DAOException, DataBaseException{
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<Payment> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("payment.SelectAll");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(processPaymentRow(rs));
            }
        });
        return list;
    }

    @Override
    public Payment findPaymentById(int id) throws DAOException, DataBaseException{
        String sql = MySqlQueryManager.getProperty("payment.SelectPaymentById");
        ConnectionWrapper con = TransactionUtil.getConnection();
        PaymentExecutable expositionDaoExecutable = new PaymentExecutable(con, id, sql);
        MySqlExecutor.execute(con, expositionDaoExecutable);
        return expositionDaoExecutable.payment;
    }

    @Override
    public List<Payment> findPaymentsByUserId(int id) throws DAOException, DataBaseException{
        ConnectionWrapper con = TransactionUtil.getConnection();
        List<Payment> list = new ArrayList<>();
        MySqlExecutor.execute(con, () -> {
            String sql = MySqlQueryManager.getProperty("payment.SelectPaymentsByUserId");
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(processPaymentRow(rs));
            }
        });
        return list;
    }

    @Override
    public Payment findPaymentByTicketId(int id) throws DAOException, DataBaseException{
        String sql = MySqlQueryManager.getProperty("payment.SelectPaymentByTicketId");
        ConnectionWrapper con = TransactionUtil.getConnection();
        PaymentExecutable expositionDaoExecutable = new PaymentExecutable(con, id, sql);
        MySqlExecutor.execute(con, expositionDaoExecutable);
        return expositionDaoExecutable.payment;
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
        return new Payment(id,date,new User.UserBuilder(userId,userLogin).build(),
                new Ticket.TicketBuilder(ticketId, ticketDescription, ticketValue,
                        new Exposition.ExpositionBuilder(expositionId, expositionTheme).build()).build());
    }

    private class PaymentExecutable implements MySqlExecutable {
        private Payment payment;
        private final ConnectionWrapper con;
        private final int id;
        private final String sql;
        PaymentExecutable(ConnectionWrapper con, int id, String sql) {
            this.con = con;
            this.id = id;
            this.sql = sql;
        }

        @Override
        public void exec() throws SQLException {
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            payment = processPaymentRow(rs);
        }
    }
}
