package com.company.exhibitions.web.controller.validation;

import java.util.Map;
import java.util.Objects;

public abstract class ControllerValidator {

    private ControllerValidator next;

    public ControllerValidator linkWith(ControllerValidator next){
        this.next = next;
        return next;
    }

    public abstract String defineAttribute(Map<String, String> map);

    public String defineNextValidator(Map<String, String> map){
        if(Objects.isNull(next)){
            return null;
        }
        return next.defineAttribute(map);
    }
}
