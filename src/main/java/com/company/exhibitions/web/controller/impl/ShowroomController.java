package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.ShowroomsCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.showroomcommand.impl.*;
import com.company.exhibitions.web.controller.Controller;

public class ShowroomController extends Controller {

    private final InsertShowroomCommand insertShowroomCommand;
    private final UpdateShowroomCommand updateShowroomCommand;
    private final DeleteShowroomCommand deleteShowroomCommand;
    private final FindAllShowroomsCommand findAllShowroomsCommand;
    private final FindShowroomByIdCommand findShowroomByIdCommand;

    public ShowroomController(){
        this.insertShowroomCommand = new InsertShowroomCommand();
        this.updateShowroomCommand = new UpdateShowroomCommand();
        this.deleteShowroomCommand = new DeleteShowroomCommand();
        this.findAllShowroomsCommand = new FindAllShowroomsCommand();
        this.findShowroomByIdCommand = new FindShowroomByIdCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch (ShowroomsCommand.valueOf(command.toUpperCase())){
            case INSERT_SHOWROOM:
                return insertShowroomCommand;
            case UPDATE_SHOWROOM:
                return updateShowroomCommand;
            case DELETE_SHOWROOM:
                return deleteShowroomCommand;
            case FIND_ALL:
                return findAllShowroomsCommand;
            case FIND_SHOWROOM_BY_ID:
                return findShowroomByIdCommand;
        }
        return defineNextCommand(command);
    }
}
