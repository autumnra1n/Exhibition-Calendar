package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.dto.Ticket;

public class TicketAmountLeftValidator {

    public boolean notLeft(Ticket ticket){
        return ticket.getAmount()<1;
    }
}
