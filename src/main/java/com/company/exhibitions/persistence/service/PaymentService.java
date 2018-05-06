package com.company.exhibitions.service;

import com.company.exhibitions.dao.PaymentDao;
import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.transaction.TransactionUtil;

import java.util.List;

public class PaymentService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertPayment(Payment payment) throws ServiceException {
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            TicketDao ticketDao = factory.getTicketDao();
            try {
                TransactionUtil.beginTransaction();
                paymentDao.insertPayment(payment);
                ticketDao.updateTicket(payment.getTicket());
                TransactionUtil.commit();

            } catch (DAOException e) {
                TransactionUtil.rollback();
            } finally {
                TransactionUtil.endTransaction();
            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePayment(Payment payment) throws ServiceException {
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                paymentDao.updatePayment(payment);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deletePayment(int id) throws ServiceException{
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                paymentDao.deletePaymentById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<Payment> findAll() throws ServiceException{
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                return paymentDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Payment findPaymentById (int id) throws ServiceException{
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                return paymentDao.findPaymentById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Payment findPaymentByTicketId (int id) throws ServiceException{
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                return paymentDao.findPaymentByTicketId(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }
    public List<Payment> findPaymentByUserId(int id) throws ServiceException{
        try {
            PaymentDao paymentDao = factory.getPaymentDao();
            try {
                return paymentDao.findPaymentsByUserId(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

}
