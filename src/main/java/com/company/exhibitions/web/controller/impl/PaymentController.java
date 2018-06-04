package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.PaymentsCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.payment.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class PaymentController extends Controller {

    private final InsertPaymentCommand insertPaymentCommand;
    private final UpdatePaymentCommand updatePaymentCommand;
    private final DeletePaymentCommand deletePaymentCommand;
    private final FindAllPaymentsCommand findAllPaymentsCommand;
    private final FindPaymentByIdCommand findPaymentByIdCommand;
    private final FindPaymentByTicketIdCommand findPaymentByTicketIdCommand;
    private final FindPaymentByUserIdCommand findPaymentByUserIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public PaymentController(Controller controller){
        super(controller);
        this.insertPaymentCommand = new InsertPaymentCommand();
        this.updatePaymentCommand = new UpdatePaymentCommand();
        this.deletePaymentCommand = new DeletePaymentCommand();
        this.findAllPaymentsCommand = new FindAllPaymentsCommand();
        this.findPaymentByIdCommand = new FindPaymentByIdCommand();
        this.findPaymentByTicketIdCommand = new FindPaymentByTicketIdCommand();
        this.findPaymentByUserIdCommand = new FindPaymentByUserIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(PaymentsCommand.class, command.toUpperCase())) {
            switch (PaymentsCommand.valueOf(command.toUpperCase())) {
                case INSERT_PAYMENT:
                    return insertPaymentCommand;
                case UPDATE_PAYMENT:
                    return updatePaymentCommand;
                case DELETE_PAYMENT:
                    return deletePaymentCommand;
                case FIND_ALL_PAYMENTS:
                    return findAllPaymentsCommand;
                case FIND_PAYMENT_BY_ID:
                    return findPaymentByIdCommand;
                case FIND_PAYMENT_BY_TICKET_ID:
                    return findPaymentByTicketIdCommand;
                case FIND_PAYMENT_BY_USER_ID:
                    return findPaymentByUserIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
