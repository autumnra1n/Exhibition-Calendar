package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ExpositionDescriptionValidator extends ControllerValidator {

    public ExpositionDescriptionValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.nonNull(map.get("expositionDescription")) && map.get("expositionDescription").length()<1000){
            return defineNextValidator(map);
        }
        else {
            return CustomRequestAttributes.INVALID_DESCRIPTION.name().toLowerCase();
        }
    }
}
