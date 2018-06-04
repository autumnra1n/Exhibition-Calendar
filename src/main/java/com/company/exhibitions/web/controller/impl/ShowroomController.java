package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.ShowroomsCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.showroom.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class ShowroomController extends Controller {

    private final InsertShowroomCommand insertShowroomCommand;
    private final UpdateShowroomCommand updateShowroomCommand;
    private final DeleteShowroomCommand deleteShowroomCommand;
    private final FindAllShowroomsCommand findAllShowroomsCommand;
    private final FindShowroomByIdCommand findShowroomByIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public ShowroomController(Controller controller){
        super(controller);
        this.insertShowroomCommand = new InsertShowroomCommand();
        this.updateShowroomCommand = new UpdateShowroomCommand();
        this.deleteShowroomCommand = new DeleteShowroomCommand();
        this.findAllShowroomsCommand = new FindAllShowroomsCommand();
        this.findShowroomByIdCommand = new FindShowroomByIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(ShowroomsCommand.class, command.toUpperCase())) {
            switch (ShowroomsCommand.valueOf(command.toUpperCase())) {
                case INSERT_SHOWROOM:
                    return insertShowroomCommand;
                case UPDATE_SHOWROOM:
                    return updateShowroomCommand;
                case DELETE_SHOWROOM:
                    return deleteShowroomCommand;
                case FIND_ALL_SHOWROOMS:
                    return findAllShowroomsCommand;
                case FIND_SHOWROOM_BY_ID:
                    return findShowroomByIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
