package com.company.exhibitions.web.command.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdLengthValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdTypeValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@AdminCredentials
public class DeleteShowroomCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final ShowroomPresenceChecker showroomPresenceChecker;
    private final IShowroomService showroomService;
    private final String pageAdmin;


    public DeleteShowroomCommand(){
        this.controllerValidator = new ShowroomIdTypeValidator(new ShowroomIdLengthValidator(null));
        this.showroomService = super.getServiceFactory().getShowroomService();
        this.showroomPresenceChecker = new ShowroomPresenceChecker();
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
                Showroom showroom = showroomService.findShowroomById(parameters);
                showroomPresenceChecker.checkShowroomPresence(request, showroom, pageAdmin, pageAdmin);
            });
            if(Objects.nonNull(request.getAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase()))){
                super.getControllerExecutor().perform(() -> {
                    showroomService.deleteShowroom(parameters);
                });
            }
            return pageAdmin;
        }
    }
}
