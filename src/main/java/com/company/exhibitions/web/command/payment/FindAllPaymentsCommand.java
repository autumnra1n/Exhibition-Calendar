package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class FindAllPaymentsCommand extends Command {

    private final String paymentPage;
    private final IPaymentService paymentService;
    private final PaymentPresenceChecker paymentPresenceChecker;
    private final PaginationUtil paginationUtil;
    private final ControllerValidator controllerValidator;

    public FindAllPaymentsCommand(){
        this.paymentPage = super.getPageManager().getProperty("page.payment");
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.paymentPresenceChecker = new PaymentPresenceChecker();
        this.paginationUtil = super.getPaginationUtil();
        this.controllerValidator = new LimitTypeValidator(
                new LimitLengthValidator(
                        new CurrentPageTypeValidator(
                                new CurrentPageLengthValidator(
                                        null))));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return paymentPage;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Integer rows = paymentService.getNumberOfRows();
                paginationUtil.doPagination(parameters, request, rows);
                List<Payment> payments = paymentService.findAll(parameters);
                return paymentPresenceChecker.checkPaymentListPresence(request, payments, paymentPage, paymentPage);
            });
        }
    }
}

