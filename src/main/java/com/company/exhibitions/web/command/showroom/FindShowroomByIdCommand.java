package com.company.exhibitions.web.command.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdLengthValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdTypeValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomPresenceChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindShowroomByIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final IShowroomService showroomService;
    private final ShowroomPresenceChecker showroomPresenceChecker;
    private final String pageShowroom;

    public FindShowroomByIdCommand(){
        this.controllerValidator = new ShowroomIdTypeValidator(new ShowroomIdLengthValidator(null));
        this.showroomService = super.getServiceFactory().getShowroomService();
        this.showroomPresenceChecker = new ShowroomPresenceChecker();
        this.pageShowroom = super.getPageManager().getProperty("page.showroom");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if (Objects.nonNull(attribute)) {
            request.setAttribute(attribute, new Object());
            return pageShowroom;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Showroom showroom = showroomService.findShowroomById(parameters);
                return showroomPresenceChecker.checkShowroomPresence(request, showroom, pageShowroom, pageShowroom);
            });
        }
    }
}
