package com.company.exhibitions.service;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ITicketService {

    void insertTicket(Map<String, String> parameters) throws ServiceException;
    void updateTicket(Map <String, String> parameters) throws ServiceException;
    void deleteTicket(Map <String, String> parameters) throws ServiceException;
    Ticket findTicket(Map <String, String> parameters) throws ServiceException;
    List<Ticket> findAll() throws ServiceException;
    Ticket findTicketById(Map <String, String> parameters) throws ServiceException;
    List<Ticket> findTicketsByExpositionId(Map <String, String> parameters) throws ServiceException;
}
