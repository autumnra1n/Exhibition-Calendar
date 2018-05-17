package com.company.exhibitions.web.command.ticketcommand.impl;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.ticketcommand.TicketCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FindTicketByExpositionIdCommand extends TicketCommand {

    private final ControllerExecutor controllerExecutor;
    private final ControllerValidator controllerValidator;

    public FindTicketByExpositionIdCommand(){
        this.controllerExecutor = new ControllerExecutor();
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        this.controllerValidator = idTypeValidator.linkWith(idLengthValidator);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        ITicketService ticketService = serviceFactory.getTicketService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.ticket");
        } else {
            return controllerExecutor.performAttributeSelect(() -> {
                List<Ticket> tickets = ticketService.findTicketsByExpositionId(extractParameters(request));
                return checkTicketListPresence(request, tickets);
            });
        }
    }
}
