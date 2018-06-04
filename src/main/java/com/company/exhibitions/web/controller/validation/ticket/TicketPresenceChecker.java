package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class TicketPresenceChecker {

    public String checkTicketPresence(HttpServletRequest request, Ticket ticket, String successPage, String failurePage){
        if(Objects.isNull(ticket)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_TICKET.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.TICKET.name().toLowerCase(), ticket);
            return successPage;
        }
    }

    public String checkTicketListPresence(HttpServletRequest request, List<Ticket> tickets, String successPage, String failurePage){
        if(tickets.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_TICKETS.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.TICKETS.name().toLowerCase(), tickets);
            return successPage;
        }
    }
}
