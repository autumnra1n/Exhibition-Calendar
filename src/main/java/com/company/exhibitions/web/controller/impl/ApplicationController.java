package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.applicationcommand.impl.LoginCommand;
import com.company.exhibitions.web.command.enums.ApplicationsCommand;
import com.company.exhibitions.web.controller.Controller;

public class ApplicationController extends Controller {

    private final LoginCommand loginCommand;

    public ApplicationController(){
        this.loginCommand = new LoginCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch (ApplicationsCommand.valueOf(command.toUpperCase())) {
            case VERIFY_LOGIN:
                return loginCommand;
        }
        return defineNextCommand(command);
    }
}
