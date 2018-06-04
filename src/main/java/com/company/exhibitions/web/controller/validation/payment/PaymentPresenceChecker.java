package com.company.exhibitions.web.controller.validation.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class PaymentPresenceChecker {

    public String checkPaymentPresence(HttpServletRequest request, Payment payment, String successPage, String failurePage){
        if(Objects.isNull(payment)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_PAYMENT.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase(), payment);
            return successPage;
        }
    }

    public String checkPaymentListPresence(HttpServletRequest request, List<Payment> payments,  String successPage, String failurePage){
        if(payments.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_PAYMENTS.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.PAYMENTS.name().toLowerCase(), payments);
            return successPage;
        }
    }
}
