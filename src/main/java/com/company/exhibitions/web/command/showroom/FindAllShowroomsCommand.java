package com.company.exhibitions.web.command.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomPresenceChecker;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindAllShowroomsCommand extends Command {

    private final IShowroomService showroomService;
    private final ShowroomPresenceChecker showroomPresenceChecker;
    private final String pageShowroom;
    private final PaginationUtil paginationUtil;
    private final ControllerValidator controllerValidator;

    public FindAllShowroomsCommand(){
        this.showroomService = super.getServiceFactory().getShowroomService();
        this.showroomPresenceChecker = new ShowroomPresenceChecker();
        this.pageShowroom = super.getPageManager().getProperty("page.showroom");
        this.paginationUtil = super.getPaginationUtil();
        this.controllerValidator = new LimitTypeValidator(
                new LimitLengthValidator(
                        new CurrentPageTypeValidator(
                                new CurrentPageLengthValidator(
                                        null))));
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
                Integer rows = showroomService.getNumberOfRows();
                paginationUtil.doPagination(parameters, request, rows);
                List<Showroom> showrooms = showroomService.findAll(parameters);
                return showroomPresenceChecker.checkShowroomListPresence(request, showrooms, pageShowroom, pageShowroom);
            });
        }
    }
}
