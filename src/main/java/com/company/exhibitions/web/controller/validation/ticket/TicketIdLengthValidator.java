package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class TicketIdLengthValidator extends ControllerValidator {

    public TicketIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("ticketId")) || Integer.valueOf(map.get("ticketId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("ticketId"))<1){
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
