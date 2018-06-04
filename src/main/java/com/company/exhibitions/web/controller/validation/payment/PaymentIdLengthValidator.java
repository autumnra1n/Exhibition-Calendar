package com.company.exhibitions.web.controller.validation.payment;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;
import java.util.Objects;

public class PaymentIdLengthValidator extends ControllerValidator {

    public PaymentIdLengthValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        if(Objects.isNull(map.get("paymentId")) || Integer.valueOf(map.get("paymentId"))>Integer.MAX_VALUE || Integer.valueOf(map.get("paymentId"))<1){
            return CustomRequestAttributes.INVALID_ID_LENGTH.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
