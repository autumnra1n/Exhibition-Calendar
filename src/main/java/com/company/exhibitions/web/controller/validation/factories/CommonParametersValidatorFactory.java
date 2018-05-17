package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.common.*;

public class CommonParametersValidatorFactory {

    public ControllerValidator getDateValidator(){
        return new DateValidator();
    }

    public ControllerValidator getDescriptionValidator(){
        return new DescriptionValidator();
    }

    public ControllerValidator getIdlengthValidator(){
        return new IdLengthValidator();
    }

    public ControllerValidator getIdTypeValidator(){
        return new IdTypeValidator();
    }

    public ControllerValidator getTimeValidator(){
        return new TimeValidator();
    }
}
