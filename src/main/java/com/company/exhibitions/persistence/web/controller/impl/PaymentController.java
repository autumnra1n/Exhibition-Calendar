package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.PaymentCommands;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.paymentcommand.*;
import com.company.exhibitions.web.controller.Controller;

public class PaymentController extends Controller {

    private final InsertPaymentCommand insertPaymentCommand;
    private final UpdatePaymentCommand updatePaymentCommand;
    private final DeletePaymentCommand deletePaymentCommand;
    private final FindAllPaymentsCommand findAllPaymentsCommand;
    private final FindPaymentByIdCommand findPaymentByIdCommand;
    private final FindPaymentByTicketIdCommand findPaymentByTicketIdCommand;
    private final FindPaymentByUserIdCommand findPaymentByUserIdCommand;

    public PaymentController(){
        this.insertPaymentCommand = new InsertPaymentCommand();
        this.updatePaymentCommand = new UpdatePaymentCommand();
        this.deletePaymentCommand = new DeletePaymentCommand();
        this.findAllPaymentsCommand = new FindAllPaymentsCommand();
        this.findPaymentByIdCommand = new FindPaymentByIdCommand();
        this.findPaymentByTicketIdCommand = new FindPaymentByTicketIdCommand();
        this.findPaymentByUserIdCommand = new FindPaymentByUserIdCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch (PaymentCommands.valueOf(command.toUpperCase())) {
            case INSERT_PAYMENT:
                return insertPaymentCommand;
            case UPDATE_PAYMENT:
                return updatePaymentCommand;
            case DELETE_PAYMENT:
                return deletePaymentCommand;
            case FIND_ALL:
                return findAllPaymentsCommand;
            case FIND_PAYMENT_BY_ID:
                return findPaymentByIdCommand;
            case FIND_PAYMENT_BY_TICKET_ID:
                return findPaymentByTicketIdCommand;
            case FIND_PAYMENT_BY_USER_ID:
                return findPaymentByUserIdCommand;
        }
        return defineNextCommand(command);
    }
}
