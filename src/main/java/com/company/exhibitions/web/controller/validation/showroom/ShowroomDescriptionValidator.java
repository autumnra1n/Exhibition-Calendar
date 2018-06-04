package com.company.exhibitions.web.controller.validation.showroom;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class ShowroomDescriptionValidator extends ControllerValidator {

    public ShowroomDescriptionValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(!Objects.isNull(map.get("showroomDescription")) && map.get("showroomDescription").length()<1000){
            return defineNextValidator(map);
        }
        else
            return CustomRequestAttributes.INVALID_DESCRIPTION.name().toLowerCase();
    }
}
