package com.company.exhibitions.web.command.paymentcommand.impl;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.paymentcommand.PaymentCommand;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class InsertPaymentCommand extends PaymentCommand {

    private final ControllerExecutor controllerExecutor;

    public InsertPaymentCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IPaymentService paymentService = serviceFactory.getPaymentService();
        Map<String, String> parameters = extractParameters(request);
        controllerExecutor.perform(() -> {
            Payment payment = paymentService.findPayment(parameters);
            checkPaymentPresence(request, payment);
        });
        if (Objects.isNull(request.getAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase()))) {
            controllerExecutor.perform(() -> {
                paymentService.insertPayment(parameters);
            });
        }
        return PageManager.getProperty("page.payment");
    }
}
