package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.TicketsCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.ticket.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class TicketController extends Controller {

    private final InsertTicketCommand insertTicketCommand;
    private final UpdateTicketCommand updateTicketCommand;
    private final DeleteTicketCommand deleteTicketCommand;
    private final FindAllTicketsCommand findAllTicketsCommand;
    private final FindTicketByIdCommand findTicketByIdCommand;
    private final FindTicketByExpositionIdCommand findTicketByExpositionIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public TicketController(Controller controller){
        super(controller);
        this.insertTicketCommand = new InsertTicketCommand();
        this.updateTicketCommand = new UpdateTicketCommand();
        this.deleteTicketCommand = new DeleteTicketCommand();
        this.findAllTicketsCommand = new FindAllTicketsCommand();
        this.findTicketByIdCommand = new FindTicketByIdCommand();
        this.findTicketByExpositionIdCommand = new FindTicketByExpositionIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(TicketsCommand.class, command.toUpperCase())) {
            switch (TicketsCommand.valueOf(command.toUpperCase())) {
                case INSERT_TICKET:
                    return insertTicketCommand;
                case UPDATE_TICKET:
                    return updateTicketCommand;
                case DELETE_TICKET:
                    return deleteTicketCommand;
                case FIND_ALL_TICKETS:
                    return findAllTicketsCommand;
                case FIND_TICKET_BY_ID:
                    return findTicketByIdCommand;
                case FIND_TICKET_BY_EXPOSITION_ID:
                    return findTicketByExpositionIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
