package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class TicketValueValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("value"));
            return defineNextValidator(map);
        } catch (Exception e){
            return CustomRequestAttributes.INVALID_TICKET_VALUE.name().toLowerCase();
        }
    }
}
