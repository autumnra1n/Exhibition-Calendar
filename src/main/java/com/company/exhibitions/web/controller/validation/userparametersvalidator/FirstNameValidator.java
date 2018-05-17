package com.company.exhibitions.web.controller.validation.userparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class FirstNameValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("firstName")) && map.get("firstName").length()<32){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_FIRST_NAME.name().toLowerCase();
    }
}
