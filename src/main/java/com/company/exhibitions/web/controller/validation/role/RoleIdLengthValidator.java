package com.company.exhibitions.web.controller.validation.role;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class RoleIdLengthValidator extends ControllerValidator {

    public RoleIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("roleId")) || Integer.valueOf(map.get("roleId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("roleId"))<1){
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
