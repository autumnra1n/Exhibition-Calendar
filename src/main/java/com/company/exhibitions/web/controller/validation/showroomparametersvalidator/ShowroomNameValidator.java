package com.company.exhibitions.web.controller.validation.showroomparametersvalidator;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class ShowroomNameValidator extends ControllerValidator {
    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("name")) && map.get("name").length()<50){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_SHOWROOM_NAME.name().toLowerCase();
    }
}
