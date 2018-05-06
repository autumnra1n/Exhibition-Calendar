package com.company.exhibitions.service;

import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;

public class TicketService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertTicket(Ticket ticket) throws ServiceException {
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                ticketDao.insertTicket(ticket);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updateTicket(Ticket ticket) throws ServiceException {
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                ticketDao.updateTicket(ticket);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteTicket(int id) throws ServiceException{
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                ticketDao.deleteTicketById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<Ticket> findAll() throws ServiceException{
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                return ticketDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Ticket findTicketById (int id) throws ServiceException{
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                return ticketDao.findTicketById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public List<Ticket> findTicketsByExpositionId(int id) throws ServiceException{
        try {
            TicketDao ticketDao = factory.getTicketDao();
            try {
                return ticketDao.findTicketsByExpositionId(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
