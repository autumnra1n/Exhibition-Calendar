package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketService implements ITicketService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Ticket> executor = new ServiceExecutor<>();

    @Override
    public void insertTicket(Map<String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(() -> ticketDao.insertTicket(buildTicket(parameters)));
    }

    @Override
    public void updateTicket(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(() -> ticketDao.updateTicket(buildTicketWithId(parameters)));
    }

    @Override
    public void deleteTicket(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(() -> ticketDao.deleteTicketById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public Ticket findTicket(Map <String, String> parameters) throws ServiceException{
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntitySelect(() -> ticketDao.findTicket(buildTicket(parameters)));
    }

    @Override
    public List<Ticket> findAll() throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntityListSelect(ticketDao::findAll);
    }

    @Override
    public Ticket findTicketById(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntitySelect(() -> ticketDao.findTicketById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public List<Ticket> findTicketsByExpositionId(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntityListSelect(() -> ticketDao.findTicketsByExpositionId(Integer.valueOf(parameters.get("id"))));
    }

    private Ticket buildTicket(Map <String, String> parameters){
        return new Ticket.TicketBuilder(parameters.get("description"),
                Integer.valueOf(parameters.get("value")),
                new Exposition.ExpositionBuilder(parameters.get("expositionTheme"))
                        .setId(Integer.valueOf(parameters.get("expositionId")))
                        .build())
                .setAmount(Integer.valueOf(parameters.get("amount")))
                .build();
    }

    private Ticket buildTicketWithId(Map <String, String> parameters){
        return new Ticket.TicketBuilder(parameters.get("description"),
                Integer.valueOf(parameters.get("value")),
                new Exposition.ExpositionBuilder(parameters.get("expositionTheme"))
                        .setId(Integer.valueOf(parameters.get("expositionId")))
                        .build())
                .setId(Integer.valueOf(parameters.get("id")))
                .setAmount(Integer.valueOf(parameters.get("amount")))
                .build();
    }
}
