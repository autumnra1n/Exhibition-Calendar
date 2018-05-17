package com.company.exhibitions.web.controller.validation.role;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class RoleNameValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("name")) && map.get("name").length()<32){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_ROLE_NAME.name().toLowerCase();
    }
}
