package com.company.exhibitions.web.controller.validation.ticket;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class TicketIdTypeValidator  extends ControllerValidator {

    public TicketIdTypeValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("ticketId"));
        } catch (Exception e) {
            return CustomRequestAttributes.INVALID_ID_TYPE.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
