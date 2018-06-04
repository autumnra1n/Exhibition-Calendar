package com.company.exhibitions.web.controller.validation.application;

import java.util.Objects;

public class CommandExistenceValidator {

    public boolean checkCommandExistence(String command){
        return Objects.nonNull(command);
    }
}
