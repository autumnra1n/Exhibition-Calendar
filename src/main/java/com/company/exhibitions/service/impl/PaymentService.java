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
import com.company.exhibitions.service.utils.ServiceEntityExecutable;
import com.company.exhibitions.service.utils.ServiceEntityListExecutable;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.transaction.Transactional;
import com.company.exhibitions.utils.DateUtil;
import com.company.exhibitions.utils.Mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentService implements IPaymentService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Payment> executor = new ServiceExecutor<>();
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();
    private final DateUtil dateUtil = new DateUtil();


    @Override
    @Transactional
    public void insertPayment(Map<String, String> parameters) throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        TicketDao ticketDao = factory.getTicketDao();
        final Payment payment = buildPayment(parameters);
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                paymentDao.insertPayment(payment);
                ticketDao.updateTicket(payment.getTicket());
            }
        });
    }

    @Override
    public void updatePayment(Map <String, String> parameters) throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                paymentDao.updatePayment(buildPaymentWithId(parameters));
            }
        });
    }

    @Override
    public void deletePayment(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                paymentDao.deletePaymentById(Integer.valueOf(parameters.get("paymentId")));
            }
        });
    }

    @Override
    public Payment findPayment(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Payment>() {
            @Override
            public Payment execute() throws DataBaseException, DAOException {
                return paymentDao.findPayment(buildPayment(parameters));
            }
        });
    }

    @Override
    public List<Payment> findAll(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Payment>() {
            @Override
            public List<Payment> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return paymentDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public Payment findPaymentById (Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Payment>() {
            @Override
            public Payment execute() throws DataBaseException, DAOException {
                return paymentDao.findPaymentById(Integer.valueOf(parameters.get("paymentId")));
            }
        });
    }

    @Override
    public Payment findPaymentByTicketId (Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Payment>() {
            @Override
            public Payment execute() throws DataBaseException, DAOException {
                return paymentDao.findPaymentByTicketId(Integer.valueOf(parameters.get("ticketId")));
            }
        });
    }

    @Override
    public List<Payment> findPaymentByUserId(Map <String, String> parameters) throws ServiceException{
        PaymentDao paymentDao = factory.getPaymentDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Payment>() {
            @Override
            public List<Payment> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return paymentDao.findPaymentsByUserId(Integer.valueOf(parameters.get("userId")), limit, offset);
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return paymentDao.getNumberOfRows();
            }
        });
    }

    @Override
    public Integer getNumberOfRowsByUserId(Map<String, String> parameters) throws ServiceException {
        PaymentDao paymentDao = factory.getPaymentDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return paymentDao.getNumberOfRowsByUserId(Integer.valueOf(parameters.get("userId")));
            }
        });
    }

    private Payment buildPayment(Map<String, String> parameters){
        return new Payment.PaymentBuilder(dateUtil.getCurrentDate(),
                new User.UserBuilder(parameters.get("userLogin")).setId(Integer.valueOf(parameters.get("userId"))).build(),
                new Ticket.TicketBuilder(parameters.get("ticketDescription"), Integer.valueOf(parameters.get("value")),
                        new Exposition.ExpositionBuilder(parameters.get("theme"))
                                .setId(Integer.valueOf(parameters.get("expositionId")))
                                .build())
                        .setId(Integer.valueOf(parameters.get("ticketId")))
                        .setAmount(Integer.valueOf(parameters.get("amount")))
                        .build())
                .build();
    }

    private Payment buildPaymentWithId(Map<String, String> parameters){
        return new Payment.PaymentBuilder(dateUtil.getCurrentDate(),
                new User.UserBuilder(parameters.get("userLogin")).setId(Integer.valueOf(parameters.get("userId"))).build(),
                new Ticket.TicketBuilder(parameters.get("ticketDescription"), Integer.valueOf(parameters.get("ticketId")),
                        new Exposition.ExpositionBuilder(parameters.get("theme"))
                                .setId(Integer.valueOf(parameters.get("expositionId")))
                                .build())
                        .setAmount(Integer.valueOf(parameters.get("amount")))
                        .build()).setId(Integer.valueOf(parameters.get("paymentId")))
                .build();
    }
}
