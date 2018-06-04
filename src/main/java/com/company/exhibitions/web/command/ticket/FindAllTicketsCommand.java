package com.company.exhibitions.web.command.ticket;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketPresenceChecker;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindAllTicketsCommand extends Command {

    private final ITicketService ticketService;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final String pageTicket;
    private final PaginationUtil paginationUtil;
    private final ControllerValidator controllerValidator;

    public FindAllTicketsCommand(){
        this.ticketService = super.getServiceFactory().getTicketService();
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.pageTicket = super.getPageManager().getProperty("page.ticket");
        this.paginationUtil = super.getPaginationUtil();
        this.controllerValidator = new LimitTypeValidator(
                new LimitLengthValidator(
                        new CurrentPageTypeValidator(
                                new CurrentPageLengthValidator(
                                        null))));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageTicket;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Integer rows = ticketService.getNumberOfRows();
                paginationUtil.doPagination(parameters, request, rows);
                List<Ticket> tickets = ticketService.findAll(parameters);
                return ticketPresenceChecker.checkTicketListPresence(request, tickets, pageTicket, pageTicket);
            });
        }
    }
}
