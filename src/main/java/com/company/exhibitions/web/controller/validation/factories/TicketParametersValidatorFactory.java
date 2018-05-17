package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.ticketparametersvalidator.TicketAmountValidator;
import com.company.exhibitions.web.controller.validation.ticketparametersvalidator.TicketDescriptionValidator;
import com.company.exhibitions.web.controller.validation.ticketparametersvalidator.TicketValueValidator;

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
