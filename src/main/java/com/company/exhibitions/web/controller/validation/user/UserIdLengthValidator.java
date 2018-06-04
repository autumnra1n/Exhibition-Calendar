package com.company.exhibitions.web.controller.validation.user;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class UserIdLengthValidator extends ControllerValidator {

    public UserIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("userId")) || Integer.valueOf(map.get("userId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("userId"))<1){
            System.out.println(map.get("userId"));
            System.out.println(map.get("userId"));
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
