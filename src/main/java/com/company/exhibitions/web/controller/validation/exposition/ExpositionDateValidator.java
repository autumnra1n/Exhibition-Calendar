package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.utils.DateUtil;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;

import java.util.Map;

public class ExpositionDateValidator extends ControllerValidator {

    private final DateUtil dateUtil;

    public ExpositionDateValidator(ControllerValidator next) {
        super(next);
        this.dateUtil = new DateUtil();
    }

    @Override
    public String defineAttribute(Map<String, String> map) {
        try{
            dateUtil.verifyDate(map.get("date"), "yyyy.MM.dd");
            return defineNextValidator(map);
        } catch(Exception e) {
            return CustomRequestAttributes.INVALID_DATE.name().toLowerCase();
        }
    }
}
