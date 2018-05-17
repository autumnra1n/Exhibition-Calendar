package com.company.exhibitions.web.controller.validation.expositionparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class ThemeLengthValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("theme")) && map.get("theme").length()<100){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_THEME_LENGTH.name().toLowerCase();
    }
}
