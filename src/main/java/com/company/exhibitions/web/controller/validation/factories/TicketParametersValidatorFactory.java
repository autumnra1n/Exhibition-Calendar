package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketAmountValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketDescriptionValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketValueValidator;

public class TicketParametersValidatorFactory {

    public ControllerValidator getTicketAmountValidator(){
        return new TicketAmountValidator();
    }

    public ControllerValidator getTicketDescriptionValidator(){
        return new TicketDescriptionValidator();
    }

    public ControllerValidator getTicketValueValidator(){
        return new TicketValueValidator();
    }
}
