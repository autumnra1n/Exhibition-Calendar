package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.role.RoleNameValidator;

public class RoleParametersValidatorFactory {

    public ControllerValidator getRoleNameValidator(){
        return new RoleNameValidator();
    }
}
