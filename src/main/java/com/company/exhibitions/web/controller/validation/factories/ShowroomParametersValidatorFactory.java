package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.showroomparametersvalidator.ShowroomLocationValidator;
import com.company.exhibitions.web.controller.validation.showroomparametersvalidator.ShowroomNameValidator;

public class ShowroomParametersValidatorFactory {

    public ControllerValidator getShowroomLocationValidator(){
        return new ShowroomLocationValidator();
    }

    public ControllerValidator getShowroomNameValidator(){
        return new ShowroomNameValidator();
    }
}
