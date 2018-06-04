package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.application.*;
import com.company.exhibitions.web.command.enums.ApplicationsCommand;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

import java.util.Objects;

public class ApplicationController extends Controller {

    private final LoginCommand loginCommand;
    private final LogoutCommand logoutCommand;
    private final ForwardToAdminPageCommand forwardToAdminPageCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public ApplicationController(Controller controller){
        super(controller);
        this.loginCommand = new LoginCommand();
        this.forwardToAdminPageCommand = new ForwardToAdminPageCommand();
        this.logoutCommand = new LogoutCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(ApplicationsCommand.class, command.toUpperCase())) {
            switch (ApplicationsCommand.valueOf(command.toUpperCase())) {
                case VERIFY_PROFILE:
                    return loginCommand;
                case LOGOUT:
                    return logoutCommand;
                case FORWARD_TO_ADMIN_PAGE:
                    return forwardToAdminPageCommand;
                case FORWARD_TO_REGISTRATION_PAGE:
                    return new ForwardToRegistrationCommand();
            }
        }
        return defineNextCommand(command);
    }
}

