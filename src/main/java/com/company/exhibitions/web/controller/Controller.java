package com.company.exhibitions.web.controller;

import com.company.exhibitions.web.command.Command;

import java.util.Objects;

public abstract class Controller {

    private Controller next;

    public Controller linkWith(Controller next){
        this.next = next;
        return next;
    }

    public abstract Command defineCommand(String command);

    public Command defineNextCommand(String command){
        if(Objects.isNull(next)){
            return null;
        }
        return next.defineCommand(command);
    }
}
