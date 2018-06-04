package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class CurrentPageTypeValidator extends ControllerValidator {

    public CurrentPageTypeValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("currentPage"));
        } catch (Exception e) {
            return CustomRequestAttributes.INVALID_CURRENT_PAGE_TYPE.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
