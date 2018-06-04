package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class TicketAmountValidator extends ControllerValidator {

    public TicketAmountValidator(ControllerValidator next) {
        super(next);
    }

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
