package com.company.exhibitions.web.controller.validation.exposition;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ExpositionPresenceChecker {

    public String checkExpositionPresence(HttpServletRequest request, Exposition exposition, String successPage, String failurePage){
        if(Objects.isNull(exposition)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_EXPOSITION.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase(), exposition);
            return successPage;
        }
    }

    public String checkExpositionListPresence(HttpServletRequest request, List<Exposition> expositions, String successPage, String failurePage){
        if(expositions.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_EXPOSITIONS.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.EXPOSITIONS.name().toLowerCase(), expositions);
            return successPage;
        }
    }
}
