//package com.company.exhibitions.service.validation;
//
//import com.company.exhibitions.dao.*;
//import com.company.exhibitions.dto.*;
//import com.company.exhibitions.exception.DAOException;
//import com.company.exhibitions.exception.DataBaseException;
//import com.company.exhibitions.exception.ServiceException;
//import com.company.exhibitions.utils.Mapper;
//import org.apache.logging.log4j.Logger;
//
//import java.util.Objects;
//
//public class ServiceValidator {
//
//    private final static Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ServiceValidator.class);
//    private final ExpositionDao expositionDao;
//    private final PaymentDao paymentDao;
//    private final RoleDao roleDao;
//    private final ShowroomDao showroomDao;
//    private final TicketDao ticketDao;
//    private final UserDao userDao;
//
//    public ServiceValidator(){
//        DaoFactory daoFactory = Mapper.getDaoFactory();
//        this.expositionDao = daoFactory.getExpositionDao();
//        this.paymentDao = daoFactory.getPaymentDao();
//        this.roleDao = daoFactory.getRoleDao();
//        this.showroomDao = daoFactory.getShowroomDao();
//        this.ticketDao = daoFactory.getTicketDao();
//        this.userDao = daoFactory.getUserDao();
//    }
//
//    public boolean validateExposition(Exposition exposition) throws ServiceException {
//        try {
//            return Objects.isNull(expositionDao.findExposition(exposition));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateExpositionById(int id) throws ServiceException{
//        try {
//            return Objects.isNull(expositionDao.findExpositionById(id));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validatePayment(Payment payment) throws ServiceException {
//        try {
//            return Objects.isNull(paymentDao.findPayment(payment));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validatePaymentById(int id) throws ServiceException{
//        try {
//            return Objects.isNull(paymentDao.findPaymentById(id));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateRole(Role role) throws ServiceException {
//        try {
//            return Objects.isNull(roleDao.findRole(role));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateRoleById(int id) throws ServiceException{
//        try {
//            return Objects.isNull(expositionDao.findExpositionById(id));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateShowroom(Showroom showroom) throws ServiceException {
//        try {
//            return Objects.isNull(showroomDao.findShowroom(showroom));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateTicket(Ticket ticket) throws ServiceException {
//        try {
//            return Objects.isNull(ticketDao.findTicket(ticket));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean isTicketsLeft(Ticket ticket) throws ServiceException{
//        try {
//            return ticketDao.findTicket(ticket).getAmount()>0;
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//
//    public boolean validateUser(User user) throws ServiceException {
//        try {
//            return Objects.isNull(userDao.findAccount(user));
//        } catch (DAOException e) {
//            LOGGER.error("DAOException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        } catch (DataBaseException e) {
//            LOGGER.error("DataBaseException occurred in " + ServiceValidator.class.getName(), e);
//            throw new ServiceException();
//        }
//    }
//}
