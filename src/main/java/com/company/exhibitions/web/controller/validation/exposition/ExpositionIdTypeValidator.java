package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class ExpositionIdTypeValidator extends ControllerValidator {

    public ExpositionIdTypeValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("expositionId"));
        } catch (Exception e) {
            return CustomRequestAttributes.INVALID_ID_TYPE.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
