package com.company.exhibitions.web.controller;

import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.application.UndefinedCommand;

import java.util.Objects;

public abstract class Controller {

    private final Controller next;
    private final UndefinedCommand undefinedCommand;

    public Controller(Controller next){
        this.next = next;
        this.undefinedCommand = new UndefinedCommand();
    }

    public abstract Command defineCommand(String command);

    public Command defineNextCommand(String command){
        if(Objects.isNull(command)||Objects.isNull(next)){
            return undefinedCommand;
        }
        return next.defineCommand(command);
    }

}
