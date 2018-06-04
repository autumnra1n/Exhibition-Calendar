package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class TicketDescriptionValidator extends ControllerValidator {

    public TicketDescriptionValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("description")) && map.get("description").length()<50){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_TICKET_DESCRIPTION.name().toLowerCase();
    }
}
