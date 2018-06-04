package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class LimitTypeValidator extends ControllerValidator {

    public LimitTypeValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("limit"));
        } catch (Exception e) {
            return CustomRequestAttributes.INVALID_LIMIT_TYPE.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
