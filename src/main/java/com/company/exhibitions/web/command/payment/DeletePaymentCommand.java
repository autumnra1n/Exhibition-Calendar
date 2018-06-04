package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdLengthValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class DeletePaymentCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final PaymentPresenceChecker  paymentPresenceChecker;
    private final String pageAdmin;
    private final IPaymentService paymentService;

    public DeletePaymentCommand(){
        this.controllerValidator = new PaymentIdTypeValidator(new PaymentIdLengthValidator(null));
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
        this.paymentPresenceChecker = new PaymentPresenceChecker();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                Payment payment = paymentService.findPaymentById(parameters);
                paymentPresenceChecker.checkPaymentPresence(request, payment, pageAdmin, pageAdmin);
            });
            if(!Objects.isNull(request.getAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase()))){
                super.getControllerExecutor().perform(() -> paymentService.deletePayment(parameters));
            }
            return pageAdmin;
        }
    }
}
