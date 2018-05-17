package com.company.exhibitions.web.command.ticket.impl;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.ticket.TicketCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.TicketParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertTicketCommand extends TicketCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public InsertTicketCommand(){
        this.controllerExecutor = new ControllerExecutor();
        TicketParametersValidatorFactory ticketParametersValidatorFactory = new TicketParametersValidatorFactory();
        ControllerValidator ticketAmountValidator = ticketParametersValidatorFactory.getTicketAmountValidator();
        ControllerValidator descriptionValidator = ticketParametersValidatorFactory.getTicketDescriptionValidator();
        ControllerValidator valueValidator = ticketParametersValidatorFactory.getTicketValueValidator();
        this.controllerValidator = ticketAmountValidator.linkWith(valueValidator).linkWith(descriptionValidator);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        ITicketService ticketService = serviceFactory.getTicketService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() -> {
                Ticket ticket = ticketService.findTicket(parameters);
                checkTicketPresence(request, ticket);
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))) {
                controllerExecutor.perform(() -> {
                    ticketService.insertTicket(extractParameters(request));
                });
            }
            return PageManager.getProperty("page.admin");
        }
    }
}