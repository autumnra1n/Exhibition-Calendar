package com.company.exhibitions.service;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IPaymentService {

    void insertPayment(Map<String, String> parameters) throws ServiceException;
    void updatePayment(Map <String, String> parameters) throws ServiceException;
    void deletePayment(Map <String, String> parameters) throws ServiceException;
    Payment findPayment(Map <String, String> parameters) throws ServiceException;
    List<Payment> findAll() throws ServiceException;
    Payment findPaymentById (Map <String, String> parameters) throws ServiceException;
    Payment findPaymentByTicketId (Map <String, String> parameters) throws ServiceException;
    List<Payment> findPaymentByUserId(Map <String, String> parameters) throws ServiceException;
}
