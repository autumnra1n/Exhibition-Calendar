package com.company.exhibitions.web.command.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomLocationValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomNameValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class InsertShowroomCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ShowroomPresenceChecker showroomPresenceChecker;
    private final IShowroomService showroomService;
    private final String pageAdmin;

    public InsertShowroomCommand(){
        this.controllerValidator = new ShowroomLocationValidator(new ShowroomNameValidator(null));
        this.showroomPresenceChecker = new ShowroomPresenceChecker();
        this.showroomService = super.getServiceFactory().getShowroomService();
        this.pageAdmin = super.getPageManager().getProperty("page.admin");
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
                Showroom showroom = showroomService.findShowroom(parameters);
                showroomPresenceChecker.checkShowroomPresence(request, showroom, pageAdmin, pageAdmin);
            });
            if (Objects.isNull(request.getAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase()))) {
                super.getControllerExecutor().perform(() -> showroomService.insertShowroom(parameters));
            }
            return pageAdmin;
        }
    }
}
