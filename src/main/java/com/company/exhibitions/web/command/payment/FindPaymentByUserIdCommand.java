package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdLengthValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentIdTypeValidator;
import com.company.exhibitions.web.controller.validation.payment.PaymentPresenceChecker;
import com.company.exhibitions.web.controller.validation.user.UserIdLengthValidator;
import com.company.exhibitions.web.controller.validation.user.UserIdTypeValidator;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindPaymentByUserIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final IPaymentService paymentService;
    private final PaymentPresenceChecker paymentPresenceChecker;
    private final String pagePayment;
    private final PaginationUtil paginationUtil;

    public FindPaymentByUserIdCommand(){
        controllerValidator = new UserIdTypeValidator(
                        new UserIdLengthValidator(
                                new LimitTypeValidator(
                                        new LimitLengthValidator(
                                                new CurrentPageTypeValidator(
                                                        new CurrentPageLengthValidator(
                                                                null))))));
        this.paymentService = super.getServiceFactory().getPaymentService();
        this.pagePayment = super.getPageManager().getProperty("page.payment");
        this.paymentPresenceChecker = new PaymentPresenceChecker();
        this.paginationUtil = Mapper.getPaginationUtil();
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
                Integer rows = paymentService.getNumberOfRowsByUserId(parameters);
                paginationUtil.doPagination(parameters, request, rows);
                List<Payment> payment = paymentService.findPaymentByUserId(parameters);
                return paymentPresenceChecker.checkPaymentListPresence(request, payment, pagePayment, pagePayment);
            });
        }
    }
}
