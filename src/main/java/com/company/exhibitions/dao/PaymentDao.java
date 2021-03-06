package com.company.exhibitions.dao;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface PaymentDao {

    void insertPayment(Payment payment) throws DAOException, DataBaseException;
    void updatePayment(Payment payment) throws DAOException, DataBaseException;
    void deletePaymentById(int id) throws DAOException, DataBaseException;
    Payment findPayment(Payment payment) throws DAOException, DataBaseException;
    List<Payment> findAll(int limit, int offset) throws DAOException, DataBaseException;
    Payment findPaymentById(int id) throws DAOException, DataBaseException;
    List<Payment> findPaymentsByUserId(int id, int limit, int offset) throws DAOException, DataBaseException;
    Payment findPaymentByTicketId(int id) throws DAOException, DataBaseException;
    Integer getNumberOfRows() throws DAOException, DataBaseException;
    Integer getNumberOfRowsByUserId(int id) throws DAOException, DataBaseException;
}
