package com.company.exhibitions.dao;

public interface DaoFactory {

    ExpositionDao getExpositionDao();
    PaymentDao getPaymentDao();
    TicketDao getTicketDao();
    RoleDao getRoleDao();
    ShowroomDao getShowroomDao();
    UserDao getUserDao();
}
