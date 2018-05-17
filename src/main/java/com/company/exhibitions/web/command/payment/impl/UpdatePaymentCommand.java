package com.company.exhibitions.web.command.payment.impl;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.payment.PaymentCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class UpdatePaymentCommand extends PaymentCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public UpdatePaymentCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        this.controllerExecutor = new ControllerExecutor();
        this.controllerValidator = idTypeValidator.linkWith(idLengthValidator);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IPaymentService paymentService = serviceFactory.getPaymentService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (!Objects.isNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.admin");
        } else {
            controllerExecutor.perform(() -> {
                Payment payment = paymentService.findPayment(parameters);
                checkPaymentPresence(request, payment);
            });
            if (!Objects.isNull(request.getAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase()))) {
                controllerExecutor.perform(() -> {
                    paymentService.updatePayment(parameters);
                });
            }
            return PageManager.getProperty("page.admin");
        }
    }
}
