package com.company.exhibitions.web.controller.validation.showroomparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class ShowroomLocationValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("location")) && map.get("location").length()<50){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_SHOWROOM_LOCATION.name().toLowerCase();
    }
}
