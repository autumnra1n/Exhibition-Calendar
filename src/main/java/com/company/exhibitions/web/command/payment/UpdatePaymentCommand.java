package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.ITicketService;
import com.company.exhibitions.service.IUserService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdLengthValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;
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

@AdminCredentials
public class UpdatePaymentCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final IPaymentService paymentService;
    private final ITicketService ticketService;
    private final IUserService userService;
    private final PaymentPresenceChecker paymentPresenceChecker;
    private final UserPresenceChecker userPresenceChecker;
    private final TicketPresenceChecker ticketPresenceChecker;
    private final String pageAdmin;

    public UpdatePaymentCommand(){
        this.controllerValidator = new PaymentIdTypeValidator(
                new PaymentIdLengthValidator(
                        new TicketIdTypeValidator(
                                new TicketIdLengthValidator(
                                        new UserIdTypeValidator(
                                                new UserIdLengthValidator(
                                                        null))))));
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.ticketService = super.getServiceFactory().getTicketService();
        this.userService = super.getServiceFactory().getUserService();
        this.paymentPresenceChecker = new PaymentPresenceChecker();
        this.userPresenceChecker = new UserPresenceChecker();
        this.ticketPresenceChecker = new TicketPresenceChecker();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> { ;
                Ticket ticket = ticketService.findTicketById(parameters);
                User user = userService.findUserById(parameters);
                ticketPresenceChecker.checkTicketPresence(request, ticket, pageAdmin, pageAdmin);
                userPresenceChecker.checkUserPresence(request, user, pageAdmin, pageAdmin);
                if (Objects.nonNull(request.getAttribute(CustomRequestAttributes.USER.name().toLowerCase())) &&
                        Objects.nonNull(request.getAttribute(CustomRequestAttributes.TICKET.name().toLowerCase()))) {
                    ticketService.addTicketFields(parameters, ticket);
                    Payment payment = paymentService.findPayment(parameters);
                    paymentPresenceChecker.checkPaymentPresence(request, payment, pageAdmin, pageAdmin);
                }
            });
            if (Objects.nonNull(request.getAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> {
                    paymentService.updatePayment(parameters);
                });
            }
            return pageAdmin;
        }
    }
}
