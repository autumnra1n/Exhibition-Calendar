package com.company.exhibitions.web.controller.validation.factories;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.user.*;

public class UserParametersValidatorFactory {

    public ControllerValidator getLoginValidator(){
        return new LoginValidator();
    }

    public ControllerValidator getPasswordValidator(){
        return new PasswordValidator();
    }

    public ControllerValidator getEmailValidator(){
        return new EmailValidator();
    }

    public ControllerValidator getFirstNameValidator(){
        return new FirstNameValidator();
    }

    public ControllerValidator getLastNameValidator(){
        return new LastNameValidator();
    }
}
