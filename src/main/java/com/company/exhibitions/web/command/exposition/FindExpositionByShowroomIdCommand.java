package com.company.exhibitions.web.command.exposition;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.ControllerValidator;
import com.company.exhibitions.web.controller.validation.UserCredentials;
import com.company.exhibitions.web.controller.validation.application.CurrentPageLengthValidator;
import com.company.exhibitions.web.controller.validation.application.CurrentPageTypeValidator;
import com.company.exhibitions.web.controller.validation.application.LimitLengthValidator;
import com.company.exhibitions.web.controller.validation.application.LimitTypeValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdLengthValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionIdTypeValidator;
import com.company.exhibitions.web.controller.validation.exposition.ExpositionPresenceChecker;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdLengthValidator;
import com.company.exhibitions.web.controller.validation.showroom.ShowroomIdTypeValidator;
import com.company.exhibitions.web.utils.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@UserCredentials
public class FindExpositionByShowroomIdCommand extends Command {

    private final ControllerValidator controllerValidator;
    private final String pageExposition;
    private final ExpositionPresenceChecker expositionPresenceChecker;
    private final IExpositionService expositionService;
    private final PaginationUtil paginationUtil;

    public FindExpositionByShowroomIdCommand(){
        controllerValidator = new ShowroomIdTypeValidator(
                        new ShowroomIdLengthValidator(
                                new LimitTypeValidator(
                                        new LimitLengthValidator(
                                                new CurrentPageTypeValidator(
                                                        new CurrentPageLengthValidator(
                                                                null))))));
        this.pageExposition = super.getPageManager().getProperty("page.exposition");
        this.expositionPresenceChecker = new ExpositionPresenceChecker();
        this.expositionService = super.getServiceFactory().getExpositionService();
        this.paginationUtil = super.getPaginationUtil();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parameters = super.extractParameters(request);
        String attribute = controllerValidator.defineAttribute(parameters);
        if(Objects.nonNull(attribute)){
            request.setAttribute(attribute, new Object());
            return pageExposition;
        } else {
            return super.getControllerExecutor().performAttributeSelect(() -> {
                Integer rows = expositionService.getNumberOfRowsByShowroomId(parameters);
                paginationUtil.doPagination(parameters, request, rows);
                List<Exposition> exposition = expositionService.findExpositionByShowroomId(parameters);
                return expositionPresenceChecker.checkExpositionListPresence(request, exposition, pageExposition, pageExposition);
            });
        }
    }
}
