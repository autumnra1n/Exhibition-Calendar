package com.company.exhibitions.web.controller.validation.role;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class RoleNameValidator extends ControllerValidator {

    public RoleNameValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("roleName")) && map.get("roleName").length()<32){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_ROLE_NAME.name().toLowerCase();
    }
}
