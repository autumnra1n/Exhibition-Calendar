package com.company.exhibitions.web.command.ticketcommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAllTicketsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        ITicketService ticketService = serviceFactory.getTicketService();
        try {
            request.setAttribute("tickets", ticketService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.ticket");
    }
}
