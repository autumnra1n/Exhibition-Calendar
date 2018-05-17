package com.company.exhibitions.web.command.ticket.impl;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.ticket.TicketCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class DeleteTicketCommand extends TicketCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public DeleteTicketCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        this.controllerValidator = idTypeValidator.linkWith(idLengthValidator);
        this.controllerExecutor = new ControllerExecutor();
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
                Ticket ticket = ticketService.findTicketById(parameters);
                checkTicketPresence(request, ticket);
            });
            if(!Objects.isNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))){
                controllerExecutor.perform(() -> ticketService.deleteTicket(parameters));
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
