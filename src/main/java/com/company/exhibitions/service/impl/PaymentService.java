package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.PaymentDao;
import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentService implements IPaymentService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Payment> executor = new ServiceExecutor<>();

    @Override
    public void insertPayment(Map<String, String> parameters) throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        TicketDao ticketDao = factory.getTicketDao();
        final Payment payment = buildPayment(parameters);
        executor.performTransaction(() -> {
            paymentDao.insertPayment(payment);
            ticketDao.updateTicket(payment.getTicket());
        });
    }

    @Override
    public void updatePayment(Map <String, String> parameters) throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        executor.perform(() -> paymentDao.updatePayment(buildPaymentWithId(parameters)));
    }

    @Override
    public void deletePayment(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        executor.perform(() -> paymentDao.deletePaymentById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public Payment findPayment(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(() -> paymentDao.findPayment(buildPayment(parameters)));
    }

    @Override
    public List<Payment> findAll() throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntityListSelect(paymentDao::findAll);
    }

    @Override
    public Payment findPaymentById (Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(() -> paymentDao.findPaymentById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public Payment findPaymentByTicketId (Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(() -> paymentDao.findPaymentByTicketId(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public List<Payment> findPaymentByUserId(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntityListSelect(() -> paymentDao.findPaymentsByUserId(Integer.valueOf(parameters.get("id"))));
    }

    private Payment buildPayment(Map<String, String> parameters){
        return new Payment.PaymentBuilder(Date.valueOf(parameters.get("date")),
                new User.UserBuilder(parameters.get("userLogin")).setId(Integer.valueOf(parameters.get("userId"))).build(),
                new Ticket.TicketBuilder(parameters.get("ticketDescription"), Integer.valueOf("ticketId"),
                        new Exposition.ExpositionBuilder(parameters.get("theme"))
                                .setId(Integer.valueOf(parameters.get("expositionId")))
                                .build())
                        .build())
                .build();
    }

    private Payment buildPaymentWithId(Map<String, String> parameters){
        return new Payment.PaymentBuilder(Date.valueOf(parameters.get("date")),
                new User.UserBuilder(parameters.get("userLogin")).setId(Integer.valueOf(parameters.get("userId"))).build(),
                new Ticket.TicketBuilder(parameters.get("ticketDescription"), Integer.valueOf("ticketId"),
                        new Exposition.ExpositionBuilder(parameters.get("theme"))
                                .setId(Integer.valueOf(parameters.get("expositionId")))
                                .build())
                        .build())
                .build();
    }
}
