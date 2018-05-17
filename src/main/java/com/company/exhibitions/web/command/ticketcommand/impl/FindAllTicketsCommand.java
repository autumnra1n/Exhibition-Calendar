package com.company.exhibitions.web.command.ticketcommand.impl;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.ticketcommand.TicketCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;
import org.omg.CORBA.TIMEOUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllTicketsCommand extends TicketCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllTicketsCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        ITicketService ticketService = serviceFactory.getTicketService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<Ticket> tickets = ticketService.findAll();
            return checkTicketListPresence(request, tickets);
        });
    }
}
