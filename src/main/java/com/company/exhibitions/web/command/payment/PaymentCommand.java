package com.company.exhibitions.web.command.payment;

import com.company.exhibitions.dto.Payment;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class PaymentCommand extends Command{

    public String checkPaymentPresence(HttpServletRequest request, Payment payment){
        if(Objects.isNull(payment)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_PAYMENT.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.payment");
        }
        else {
            request.setAttribute(CustomRequestAttributes.PAYMENT.name().toLowerCase(), payment);
            return PageManager.getProperty("page.payment");
        }
    }

    public String checkPaymentListPresence(HttpServletRequest request, List<Payment> payments){
        if(payments.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_PAYMENTS.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.payment");
        }
        else {
            request.setAttribute(CustomRequestAttributes.PAYMENTS.name().toLowerCase(), payments);
            return PageManager.getProperty("page.payment");
        }
    }
}
