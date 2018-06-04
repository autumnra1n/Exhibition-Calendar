package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;
import com.company.exhibitions.web.controller.validation.ticket.TicketAmountLeftValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdLengthValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdTypeValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketPresenceChecker;
import com.company.exhibitions.web.controller.validation.user.UserIdLengthValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdTypeValidator;
import com.company.exhibitions.web.controller.validation.user.UserPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class InsertPaymentCommand extends Command {

    private final IPaymentService paymentService;
    private final ITicketService ticketService;
    private final IUserService userService;
    private final PaymentPresenceChecker paymentPresenceChecker;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final UserPresenceChecker userPresenceChecker;
    private final ControllerValidator controllerValidator;
    private final TicketAmountLeftValidator ticketAmountLeftValidator;
    private final String pagePayment;

    public InsertPaymentCommand(){
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.ticketService = super.getServiceFactory().getTicketService();
        this.userService = super.getServiceFactory().getUserService();
        this.ticketAmountLeftValidator = new TicketAmountLeftValidator();
        this.controllerValidator = new TicketIdTypeValidator(
                new TicketIdLengthValidator(
                        new UserIdTypeValidator(
                                new UserIdLengthValidator(
                                        null))));
        this.paymentPresenceChecker = new PaymentPresenceChecker();
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.userPresenceChecker = new UserPresenceChecker();
        this.pagePayment = super.getPageManager().getProperty("page.payment");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pagePayment;
        } else {
            super.getControllerExecutor().perform(() -> {
                Ticket ticket = ticketService.findTicketById(parameters);
                User user = userService.findUserById(parameters);
                ticketPresenceChecker.checkTicketPresence(request, ticket, pagePayment, pagePayment);
                userPresenceChecker.checkUserPresence(request, user, pagePayment, pagePayment);
                if(Objects.nonNull(request.getAttribute(CustomRequestAttributes.USER.name().toLowerCase())) &&
                        Objects.nonNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))) {
                    ticketService.addTicketFields(parameters, ticket);
                    ticketService.acquirePayment(parameters, ticket);
                    Payment payment = paymentService.findPayment(parameters);
                    paymentPresenceChecker.checkPaymentPresence(request, payment, pagePayment, pagePayment);
                }
                if(ticketAmountLeftValidator.notLeft(ticket)){
                    request.setAttribute(CustomRequestAttributes.NO_TICKETS_LEFT.name().toLowerCase(), new Object());
                }
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase()))&&
                    (Objects.nonNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase())))&&
            Objects.isNull(request.getAttribute(CustomRequestAttributes.NO_TICKETS_LEFT.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> paymentService.insertPayment(parameters));
            }
        }
            return pagePayment;
    }
}

