package com.company.exhibitions.web.command.payment.impl;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.payment.PaymentCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllPaymentsCommand extends PaymentCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllPaymentsCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IPaymentService paymentService = serviceFactory.getPaymentService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<Payment> payments = paymentService.findAll();
            return checkPaymentListPresence(request, payments);
        });
    }
}
