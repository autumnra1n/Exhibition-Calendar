package com.company.exhibitions.web.command.ticket;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdLengthValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdTypeValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionPresenceChecker;
import com.company.exhibitions.web.controller.validation.ticket.TicketAmountValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketDescriptionValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketPresenceChecker;
import com.company.exhibitions.web.controller.validation.ticket.TicketValueValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class InsertTicketCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final ITicketService ticketService;
    private final IExpositionService expositionService;
    private final String pageAdmin;

    public InsertTicketCommand(){
        this.controllerValidator = new TicketAmountValidator(
                new TicketValueValidator(
                        new TicketDescriptionValidator(
                                new ExpositionIdTypeValidator(
                                        new ExpositionIdLengthValidator(null)))));
        this.ticketService = super.getServiceFactory().getTicketService();
        this.expositionService = super.getServiceFactory().getExpositionService();
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
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
                Ticket ticket = ticketService.findTicket(parameters);
                Exposition exposition = expositionService.findExpositionById(parameters);
                ticketPresenceChecker.checkTicketPresence(request, ticket, pageAdmin, pageAdmin);
                expositionPresenceChecker.checkExpositionPresence(request, exposition, pageAdmin, pageAdmin);
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))&&
                    Objects.nonNull(request.getAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> ticketService.insertTicket(extractParameters(request)));
            }
            return pageAdmin;
        }
    }
}
