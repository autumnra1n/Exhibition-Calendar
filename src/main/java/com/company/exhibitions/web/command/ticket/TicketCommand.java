package com.company.exhibitions.web.command.ticket;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class TicketCommand extends Command {

    public String checkTicketPresence(HttpServletRequest request, Ticket ticket){
        if(Objects.isNull(ticket)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_TICKET.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.ticket");
        }
        else {
            request.setAttribute(CustomRequestAttributes.TICKET.name().toLowerCase(), ticket);
            return PageManager.getProperty("page.ticket");
        }
    }

    public String checkTicketListPresence(HttpServletRequest request, List<Ticket> tickets){
        if(tickets.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_TICKETS.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.ticket");
        }
        else {
            request.setAttribute(CustomRequestAttributes.TICKETS.name().toLowerCase(), tickets);
            return PageManager.getProperty("page.ticket");
        }
    }
}
