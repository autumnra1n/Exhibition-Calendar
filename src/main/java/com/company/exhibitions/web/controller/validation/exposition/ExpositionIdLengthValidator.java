package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ExpositionIdLengthValidator extends ControllerValidator {

    public ExpositionIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("expositionId")) || Integer.valueOf(map.get("expositionId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("expositionId"))<1){
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
