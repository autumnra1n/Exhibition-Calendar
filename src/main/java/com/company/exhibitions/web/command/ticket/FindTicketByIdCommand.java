package com.company.exhibitions.web.command.ticket;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdLengthValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdTypeValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindTicketByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ITicketService ticketService;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final String pageTicket;

    public FindTicketByIdCommand(){
        this.controllerValidator = new TicketIdTypeValidator(new TicketIdLengthValidator(null));
        this.ticketService = super.getServiceFactory().getTicketService();
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.pageTicket = super.getPageManager().getProperty("page.ticket");
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
                Ticket ticket = ticketService.findTicketById(parameters);
                return ticketPresenceChecker.checkTicketPresence(request, ticket, pageTicket, pageTicket);
            });
        }
    }
}
