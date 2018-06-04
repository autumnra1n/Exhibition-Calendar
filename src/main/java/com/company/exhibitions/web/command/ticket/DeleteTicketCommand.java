package com.company.exhibitions.web.command.ticket;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdLengthValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdTypeValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class DeleteTicketCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ITicketService ticketService;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final String pageAdmin;

    public DeleteTicketCommand(){
        this.controllerValidator = new TicketIdTypeValidator(new TicketIdLengthValidator(null));
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.ticketService = super.getServiceFactory().getTicketService();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                Ticket ticket = ticketService.findTicketById(parameters);
                ticketPresenceChecker.checkTicketPresence(request, ticket, pageAdmin, pageAdmin);
            });
            if(Objects.nonNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))){
                super.getControllerExecutor().perform(() -> ticketService.deleteTicket(parameters));
            }
            return pageAdmin;
        }
    }
}
