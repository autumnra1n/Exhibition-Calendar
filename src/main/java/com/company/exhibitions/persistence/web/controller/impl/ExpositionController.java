package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.ExpositionCommands;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.expositioncommand.*;
import com.company.exhibitions.web.controller.Controller;

public class ExpositionController extends Controller {

    private final InsertExpositionCommand insertExpositionCommand;
    private final UpdateExpositionCommand updateExpositionCommand;
    private final DeleteExpositionCommand deleteExpositionCommand;
    private final FindAllExpositionsCommand findAllExpositionsCommand;
    private final FindExpositionByIdCommand findExpositionByIdCommand;
    private final FindExpositionByShowroomIdCommand findExpositionByShowroomIdCommand;

    public ExpositionController(){
        this.insertExpositionCommand = new InsertExpositionCommand();
        this.updateExpositionCommand = new UpdateExpositionCommand();
        this.deleteExpositionCommand = new DeleteExpositionCommand();
        this.findAllExpositionsCommand = new FindAllExpositionsCommand();
        this.findExpositionByIdCommand = new FindExpositionByIdCommand();
        this.findExpositionByShowroomIdCommand = new FindExpositionByShowroomIdCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch (ExpositionCommands.valueOf(command.toUpperCase())) {
            case INSERT_EXPOSITION:
                return insertExpositionCommand;
            case UPDATE_EXPOSITION:
                return updateExpositionCommand;
            case DELETE_EXPOSITION:
                return deleteExpositionCommand;
            case FIND_ALL:
                return findAllExpositionsCommand;
            case FIND_EXPOSITION_BY_ID:
                return findExpositionByIdCommand;
            case FIND_EXPOSITION_BY_SHOWROOM_ID:
                return findExpositionByShowroomIdCommand;
        }
        return defineNextCommand(command);
    }
}
