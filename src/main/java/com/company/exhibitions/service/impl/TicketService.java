package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.TicketDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.utils.ServiceEntityExecutable;
import com.company.exhibitions.service.utils.ServiceEntityListExecutable;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketService implements ITicketService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Ticket> executor = new ServiceExecutor<>();
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();

    @Override
    public void insertTicket(Map<String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                ticketDao.insertTicket(buildTicket(parameters));
            }
        });
    }

    @Override
    public void updateTicket(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                ticketDao.updateTicket(buildTicketWithId(parameters));
            }
        });
    }

    @Override
    public void deleteTicket(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                ticketDao.deleteTicketById(Integer.valueOf(parameters.get("ticketId")));
            }
        });
    }

    @Override
    public Ticket findTicket(Map <String, String> parameters) throws ServiceException{
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Ticket>() {
            @Override
            public Ticket execute() throws DataBaseException, DAOException {
                return ticketDao.findTicket(buildTicket(parameters));
            }
        });
    }

    @Override
    public List<Ticket> findAll(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Ticket>() {
            @Override
            public List<Ticket> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return ticketDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public Ticket findTicketById(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Ticket>() {
            @Override
            public Ticket execute() throws DataBaseException, DAOException {
                return ticketDao.findTicketById(Integer.valueOf(parameters.get("ticketId")));
            }
        });
    }

    @Override
    public List<Ticket> findTicketsByExpositionId(Map <String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Ticket>() {
            @Override
            public List<Ticket> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return ticketDao.findTicketsByExpositionId(Integer.valueOf(parameters.get("expositionId")), limit, offset);
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return ticketDao.getNumberOfRows();
            }
        });
    }

    @Override
    public Integer getNumberOfRowsByExpositionId(Map<String, String> parameters) throws ServiceException {
        TicketDao ticketDao = factory.getTicketDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return ticketDao.getNumberOfRowsByExpositionId(Integer.valueOf(parameters.get("expositionId")));
            }
        });
    }

    @Override
    public void acquirePayment(Map<String, String> parameters, Ticket ticket){
        parameters.put("amount", String.valueOf(ticket.getAmount()-1));
    }

    @Override
    public void addTicketFields(Map<String, String> parameters, Ticket ticket){
        parameters.put("ticketDescription", ticket.getDescription());
        parameters.put("theme", ticket.getExposition().getTheme());
        parameters.put("expositionId", String.valueOf(ticket.getExposition().getId()));
        parameters.put("amount", String.valueOf(ticket.getAmount()));
        parameters.put("value", String.valueOf(ticket.getValue()));
        parameters.put("ticketId", String.valueOf(ticket.getId()));
        parameters.put("amount", String.valueOf(ticket.getAmount()));
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
                .setId(Integer.valueOf(parameters.get("ticketId")))
                .setAmount(Integer.valueOf(parameters.get("amount")))
                .build();
    }
}
