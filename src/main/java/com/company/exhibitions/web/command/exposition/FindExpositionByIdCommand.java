package com.company.exhibitions.web.command.exposition;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdLengthValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdTypeValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindExpositionByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final String pageExposition;
    private final IExpositionService expositionService;

    public FindExpositionByIdCommand(){
        controllerValidator = new ExpositionIdTypeValidator(new ExpositionIdLengthValidator(null));
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.pageExposition = super.getPageManager().getProperty("page.exposition");
        this.expositionService = super.getServiceFactory().getExpositionService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(Objects.nonNull(attribute)){
            request.setAttribute(attribute, new Object());
            return pageExposition;
        }
        else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Exposition exposition = expositionService.findExpositionById(parameters);
                return expositionPresenceChecker.checkExpositionPresence(request, exposition, pageExposition, pageExposition);
            });
        }
    }
}
