package com.company.exhibitions.web.command.expositioncommand;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class ExpositionCommand extends Command {

    public String checkExpositionPresence(HttpServletRequest request, Exposition exposition){
        if(Objects.isNull(exposition)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_EXPOSITION.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.exposition");
        }
        else {
            request.setAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase(), exposition);
            return PageManager.getProperty("page.exposition");
        }
    }

    public String checkExpositionListPresence(HttpServletRequest request, List<Exposition> expositions){
        if(expositions.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_EXPOSITIONS.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.exposition");
        }
        else {
            request.setAttribute(CustomRequestAttributes.EXPOSITIONS.name().toLowerCase(), expositions);
            return PageManager.getProperty("page.exposition");
        }
    }
}
