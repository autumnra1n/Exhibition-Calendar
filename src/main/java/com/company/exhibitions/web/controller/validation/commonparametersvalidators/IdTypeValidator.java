package com.company.exhibitions.web.controller.validation.commonparametersvalidators;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class IdTypeValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        try{
            Integer.parseInt(map.get("id"));
            return defineNextValidator(map);
        } catch(Exception e) {
            return CustomRequestAttributes.INVALID_ID_TYPE.name().toLowerCase();
        }
    }
}
