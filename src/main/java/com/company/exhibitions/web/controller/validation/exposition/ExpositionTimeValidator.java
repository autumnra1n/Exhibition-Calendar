package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.utils.TimeUtil;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class ExpositionTimeValidator extends ControllerValidator {

    private final TimeUtil timeUtil;

    public ExpositionTimeValidator(ControllerValidator next) {
        super(next);
        this.timeUtil = new TimeUtil();
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try{
            timeUtil.verifyTime(map.get("time"), "HH:mm");
            return defineNextValidator(map);
        } catch(Exception e) {
            e.printStackTrace();
            return CustomRequestAttributes.INVALID_TIME.name().toLowerCase();
        }
    }
}
