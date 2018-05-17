package com.company.exhibitions.web.controller.validation.user;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class LastNameValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("lastName")) && map.get("lastName").length()<32){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_LAST_NAME.name().toLowerCase();
    }
}
