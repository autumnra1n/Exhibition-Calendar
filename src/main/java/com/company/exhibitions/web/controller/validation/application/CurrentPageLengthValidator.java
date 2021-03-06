package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class CurrentPageLengthValidator extends ControllerValidator {

    public CurrentPageLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("currentPage")) || Integer.valueOf(map.get("currentPage"))>Integer.MAX_VALUE || Integer.valueOf(map.get("currentPage"))<1){
            return CustomRequestAttributes.INVALID_CURRENT_PAGE_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
