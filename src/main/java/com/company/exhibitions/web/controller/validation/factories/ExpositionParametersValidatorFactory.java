package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.exposition.ThemeLengthValidator;

public class ExpositionParametersValidatorFactory {

    public ControllerValidator getThemeLengthValidator(){
        return new ThemeLengthValidator();
    }
}
