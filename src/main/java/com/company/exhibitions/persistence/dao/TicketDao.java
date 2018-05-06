package com.company.exhibitions.dao;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface TicketDao {

    void insertTicket(Ticket ticket) throws DAOException, DataBaseException;
    void updateTicket(Ticket ticket) throws DAOException, DataBaseException;
    void deleteTicketById(int id) throws DAOException, DataBaseException;
    List<Ticket> findAll() throws DAOException, DataBaseException;
    Ticket findTicketById(int id) throws DAOException, DataBaseException;
    List<Ticket> findTicketsByExpositionId(int id) throws DAOException, DataBaseException;
}
