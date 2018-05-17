package com.company.exhibitions.web.command.paymentcommand.impl;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.paymentcommand.PaymentCommand;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.factories.CommonParametersValidatorFactory;
import com.company.exhibitions.web.utils.ControllerExecutor;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FindPaymentByTicketIdCommand extends PaymentCommand {

    private final ControllerValidator controllerValidator;
    private final ControllerExecutor controllerExecutor;

    public FindPaymentByTicketIdCommand(){
        CommonParametersValidatorFactory commonParametersValidatorFactory = new CommonParametersValidatorFactory();
        ControllerValidator idTypeValidator = commonParametersValidatorFactory.getIdTypeValidator();
        ControllerValidator idLengthValidator = commonParametersValidatorFactory.getIdlengthValidator();
        controllerValidator = idTypeValidator.linkWith(idLengthValidator);
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IPaymentService paymentService = serviceFactory.getPaymentService();
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(!Objects.isNull(attribute)){
            request.setAttribute(attribute, new Object());
            return PageManager.getProperty("page.payment");
        } else {
            return controllerExecutor.performAttributeSelect(() -> {
                Payment payment = paymentService.findPaymentByTicketId(extractParameters(request));
                return checkPaymentPresence(request, payment);
            });
        }
    }
}
