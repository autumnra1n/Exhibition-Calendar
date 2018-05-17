package com.company.exhibitions.web.controller.validation.commonparametersvalidators;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.sql.Date;
import java.util.Map;

public class DateValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        try{
            Date.valueOf(map.get("date"));
            return defineNextValidator(map);
        } catch(Exception e) {
            return CustomRequestAttributes.INVALID_DATE.name().toLowerCase();
        }
    }
}
