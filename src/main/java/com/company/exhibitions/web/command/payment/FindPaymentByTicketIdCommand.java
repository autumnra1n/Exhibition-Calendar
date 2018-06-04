package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdLengthValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdLengthValidator;
import com.company.exhibitions.web.controller.validation.ticket.TicketIdTypeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindPaymentByTicketIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final IPaymentService paymentService;
    private final PaymentPresenceChecker paymentPresenceChecker;
    private final String paymentPage;

    public FindPaymentByTicketIdCommand(){
        controllerValidator = new TicketIdTypeValidator(
                        new TicketIdLengthValidator(
                                null));
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.paymentPresenceChecker = new PaymentPresenceChecker();
        this.paymentPage = super.getPageManager().getProperty("page.payment");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(Objects.nonNull(attribute)){
            request.setAttribute(attribute, new Object());
            return paymentPage;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Payment payment = paymentService.findPaymentByTicketId(parameters);
                return paymentPresenceChecker.checkPaymentPresence(request, payment, paymentPage, paymentPage);
            });
        }
    }
}
