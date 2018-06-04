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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindPaymentByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final String pagePayment;
    private final IPaymentService paymentService;
    private final PaymentPresenceChecker paymentPresenceChecker;

    public FindPaymentByIdCommand(){
        controllerValidator = new PaymentIdTypeValidator(new PaymentIdLengthValidator(null));
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.pagePayment = super.getPageManager().getProperty("page.payment");
        this.paymentPresenceChecker = new PaymentPresenceChecker();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pagePayment;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() ->{
                Payment payment = paymentService.findPaymentById(parameters);
                return paymentPresenceChecker.checkPaymentPresence(request, payment, pagePayment, pagePayment);
            });
        }
    }
}
