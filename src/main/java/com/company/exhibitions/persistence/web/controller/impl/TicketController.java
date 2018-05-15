package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.TicketCommands;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.ticketcommand.*;
import com.company.exhibitions.web.controller.Controller;

public class TicketController extends Controller {

    private final InsertTicketCommand insertTicketCommand;
    private final UpdateTicketCommand updateTicketCommand;
    private final DeleteTicketCommand deleteTicketCommand;
    private final FindAllTicketsCommand findAllTicketsCommand;
    private final FindTicketByIdCommand findTicketByIdCommand;
    private final FindTicketByExpositionIdCommand findTicketByExpositionIdCommand;

    public TicketController() {
        this.insertTicketCommand = new InsertTicketCommand();
        this.updateTicketCommand = new UpdateTicketCommand();
        this.deleteTicketCommand = new DeleteTicketCommand();
        this.findAllTicketsCommand = new FindAllTicketsCommand();
        this.findTicketByIdCommand = new FindTicketByIdCommand();
        this.findTicketByExpositionIdCommand = new FindTicketByExpositionIdCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch(TicketCommands.valueOf(command.toUpperCase())){
            case INSERT_TICKET:
                return insertTicketCommand;
            case UPDATE_TICKET:
                return updateTicketCommand;
            case DELETE_TICKET:
                return deleteTicketCommand;
            case FIND_ALL:
                return findAllTicketsCommand;
            case FIND_TICKET_BY_ID:
                return findTicketByIdCommand;
            case FIND_TICKET_BY_EXPOSITION_ID:
                return findTicketByExpositionIdCommand;
        }
        return defineNextCommand(command);
    }
}
