package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.ExpositionsCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.exposition.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class ExpositionController extends Controller {

    private final InsertExpositionCommand insertExpositionCommand;
    private final UpdateExpositionCommand updateExpositionCommand;
    private final DeleteExpositionCommand deleteExpositionCommand;
    private final FindAllExpositionsCommand findAllExpositionsCommand;
    private final FindExpositionByIdCommand findExpositionByIdCommand;
    private final FindExpositionByShowroomIdCommand findExpositionByShowroomIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public ExpositionController(Controller controller){
        super(controller);
        this.insertExpositionCommand = new InsertExpositionCommand();
        this.updateExpositionCommand = new UpdateExpositionCommand();
        this.deleteExpositionCommand = new DeleteExpositionCommand();
        this.findAllExpositionsCommand = new FindAllExpositionsCommand();
        this.findExpositionByIdCommand = new FindExpositionByIdCommand();
        this.findExpositionByShowroomIdCommand = new FindExpositionByShowroomIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(ExpositionsCommand.class, command.toUpperCase())) {
            switch (ExpositionsCommand.valueOf(command.toUpperCase())) {
                case INSERT_EXPOSITION:
                    return insertExpositionCommand;
                case UPDATE_EXPOSITION:
                    return updateExpositionCommand;
                case DELETE_EXPOSITION:
                    return deleteExpositionCommand;
                case FIND_ALL_EXPOSITIONS:
                    return findAllExpositionsCommand;
                case FIND_EXPOSITION_BY_ID:
                    return findExpositionByIdCommand;
                case FIND_EXPOSITION_BY_SHOWROOM_ID:
                    return findExpositionByShowroomIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
