package com.company.exhibitions.web.command.exposition;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdLengthValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdTypeValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class DeleteExpositionCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final String pageAdmin;
    private final IExpositionService expositionService;

    public DeleteExpositionCommand(){
        this.controllerValidator = new ExpositionIdTypeValidator(new ExpositionIdLengthValidator(null));
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
        this.expositionService = super.getServiceFactory().getExpositionService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(Objects.nonNull(attribute)){
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                Exposition exposition = expositionService.findExpositionById(parameters);
                expositionPresenceChecker.checkExpositionPresence(request, exposition,
                        pageAdmin, pageAdmin);
            });
            if(Objects.nonNull(request.getAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase()))){
                super.getControllerExecutor().perform(() -> expositionService.deleteExposition(parameters));
            }
            return pageAdmin;
        }

    }
}
