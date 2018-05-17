package com.company.exhibitions.web.command.paymentcommand.impl;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.paymentcommand.PaymentCommand;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

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
