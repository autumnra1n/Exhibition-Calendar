package com.company.exhibitions.web.controller.validation.common;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.sql.Time;
import java.util.Map;

public class TimeValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        try{
            Time.valueOf(map.get("time"));
            return defineNextValidator(map);
        } catch(Exception e) {
            return CustomRequestAttributes.INVALID_TIME.name().toLowerCase();
        }
    }
}
