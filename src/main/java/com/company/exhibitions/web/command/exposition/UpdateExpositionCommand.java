package com.company.exhibitions.web.command.exposition;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.exposition.*;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class UpdateExpositionCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final ShowroomPresenceChecker showroomPresenceChecker;
    private final String pageAdmin;
    private final IExpositionService expositionService;
    private final IShowroomService showroomService;


    public UpdateExpositionCommand(){
        this.controllerValidator = new ExpositionIdTypeValidator(
                new ExpositionIdLengthValidator(
                        new ExpositionDateValidator(
                                new ExpositionTimeValidator(
                                        new ExpositionDescriptionValidator(
                                                new ThemeLengthValidator(
                                                        null))))));
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.showroomPresenceChecker = new ShowroomPresenceChecker();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
        this.expositionService = super.getServiceFactory().getExpositionService();
        this.showroomService = super.getServiceFactory().getShowroomService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageAdmin;
        } else {
            super.getControllerExecutor().perform(() -> {
                Showroom showroom = showroomService.findShowroomById(parameters);
                Exposition exposition = expositionService.findExpositionById(parameters);
                expositionPresenceChecker.checkExpositionPresence(request, exposition, pageAdmin, pageAdmin);
                showroomPresenceChecker.checkShowroomPresence(request, showroom, pageAdmin, pageAdmin);
            });
            if (Objects.nonNull(request.getAttribute(CustomRequestAttributes.EXPOSITION.name().toLowerCase()))&&
                    Objects.nonNull(request.getAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> expositionService.updateExposition(parameters));
            }
            return pageAdmin;
        }
    }
}
