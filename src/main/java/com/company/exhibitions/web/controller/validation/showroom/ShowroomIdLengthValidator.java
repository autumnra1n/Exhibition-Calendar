package com.company.exhibitions.web.controller.validation.showroom;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class ShowroomIdLengthValidator extends ControllerValidator {

    public ShowroomIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("showroomId")) || Integer.valueOf(map.get("showroomId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("showroomId"))<1){
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
