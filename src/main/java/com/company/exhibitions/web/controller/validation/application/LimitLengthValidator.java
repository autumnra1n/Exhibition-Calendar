package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class LimitLengthValidator extends ControllerValidator {

    public LimitLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("limit")) || Integer.valueOf(map.get("limit"))>Integer.MAX_VALUE || Integer.valueOf(map.get("limit"))<1){
            return CustomRequestAttributes.INVALID_LIMIT_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
