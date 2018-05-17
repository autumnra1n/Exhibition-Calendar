package com.company.exhibitions.web.controller.validation.commonparametersvalidators;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class DescriptionValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("description")) && map.get("description").length()<1000){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_DESCRIPTION.name().toLowerCase();
    }
}
