package com.company.exhibitions.web.controller.validation.commonparametersvalidators;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class IdLengthValidator extends ControllerValidator {

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("id")) && Integer.valueOf(map.get("id"))<Integer.MAX_VALUE){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
    }
}
