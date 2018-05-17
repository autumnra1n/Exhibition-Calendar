package com.company.exhibitions.dao.mysql;

import com.company.exhibitions.dao.*;

public class MySqlDaoFactory implements DaoFactory {

    private final ExpositionDao mySqlExpositionDao;
    private final PaymentDao paymentDao;
    private final TicketDao ticketDao;
    private final RoleDao roleDao;
    private final ShowroomDao showroomDao;
    private final UserDao userDao;

    public MySqlDaoFactory(){
        this.mySqlExpositionDao = new MySqlExpositionDao();
        this.paymentDao = new MySqlPaymentDao();
        this.ticketDao = new MySqlTicketDao();
        this.roleDao = new MySqlRoleDao();
        this.showroomDao = new MySqlShowroomDao();
        this.userDao = new MySqlUserDao();
    }

    @Override
    public ExpositionDao getExpositionDao(){
        return mySqlExpositionDao;
    }

    @Override
    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    @Override
    public TicketDao getTicketDao(){
        return ticketDao;
    }

    @Override
    public RoleDao getRoleDao(){
        return roleDao;
    }

    @Override
    public ShowroomDao getShowroomDao(){
        return showroomDao;
    }

    @Override
    public UserDao getUserDao(){
        return userDao;
    }
}
