package com.company.exhibitions.web.controller.validation.ticketparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class TicketAmountValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("amount"));
            return defineNextValidator(map);
        } catch (Exception e){
            return CustomRequestAttributes.INVALID_TICKET_AMOUNT.name().toLowerCase();
        }
    }
}
