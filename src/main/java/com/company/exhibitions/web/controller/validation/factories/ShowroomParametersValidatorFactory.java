package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomLocationValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomNameValidator;

public class ShowroomParametersValidatorFactory {

    public ControllerValidator getShowroomLocationValidator(){
        return new ShowroomLocationValidator();
    }

    public ControllerValidator getShowroomNameValidator(){
        return new ShowroomNameValidator();
    }
}
