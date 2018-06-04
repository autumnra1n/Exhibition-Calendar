package com.company.exhibitions.web.controller.validation.payment;

import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class PaymentIdTypeValidator extends ControllerValidator {

    public PaymentIdTypeValidator(ControllerValidator next) {
        super(next);
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("paymentId"));
        } catch (Exception e) {
            return CustomRequestAttributes.INVALID_ID_TYPE.name().toLowerCase();
        }
        return defineNextValidator(map);
    }
}
