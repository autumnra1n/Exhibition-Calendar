package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.*;

public class MySqlDaoFactory {
    private static final MySqlDaoFactory factory = new MySqlDaoFactory();
    private ExpositionDao mySqlExpositionDao = new MySqlExpositionDao();
    private PaymentDao paymentDao = new MySqlPaymentDao();
    private TicketDao ticketDao = new MySqlTicketDao();
    private RoleDao roleDao = new MySqlRoleDao();
    private ShowroomDao showroomDao = new MySqlShowroomDao();
    private UserDao userDao = new MySqlUserDao();

    private MySqlDaoFactory(){}

    public static MySqlDaoFactory getInstance(){
        return factory;
    }

    public ExpositionDao getExpositionDao(){
        return mySqlExpositionDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public TicketDao getTicketDao(){
        return ticketDao;
    }

    public RoleDao getRoleDao(){
        return roleDao;
    }

    public ShowroomDao getShowroomDao(){
        return showroomDao;
    }

    public UserDao getUserDao(){
        return userDao;
    }
}
