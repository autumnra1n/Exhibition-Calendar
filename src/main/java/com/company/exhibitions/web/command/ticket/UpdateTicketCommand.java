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
import com.company.exhibitions.web.controller.validation.ticket.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class UpdateTicketCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final ITicketService ticketService;
    private final IExpositionService expositionService;
    private final String pageAdmin;

    public UpdateTicketCommand(){
        this.controllerValidator = new TicketIdTypeValidator(
                new TicketIdLengthValidator(
                        new TicketAmountValidator(
                                new TicketDescriptionValidator(
                                        new TicketValueValidator(
                                                new ExpositionIdTypeValidator(
                                                        new ExpositionIdLengthValidator(
                                                                null)))))));
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.ticketService = super.getServiceFactory().getTicketService();
        this.expositionService = super.getServiceFactory().getExpositionService();
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
                Exposition exposition = expositionService.findExpositionById(parameters);
                Ticket ticket = ticketService.findTicketById(parameters);
                ticketPresenceChecker.checkTicketPresence(request, ticket, pageAdmin, pageAdmin);
                expositionPresenceChecker.checkExpositionPresence(request, exposition, pageAdmin, pageAdmin);
            });
            if (Objects.nonNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))&&
                    Objects.nonNull(request.getAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> ticketService.updateTicket(parameters));
            }
            return pageAdmin;
        }
    }
}
