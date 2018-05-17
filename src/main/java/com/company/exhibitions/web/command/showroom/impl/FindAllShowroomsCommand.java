package com.company.exhibitions.web.command.showroom.impl;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.showroom.ShowroomCommand;
import com.company.exhibitions.web.utils.ControllerExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllShowroomsCommand extends ShowroomCommand {

    private final ControllerExecutor controllerExecutor;

    public FindAllShowroomsCommand(){
        this.controllerExecutor = new ControllerExecutor();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IShowroomService showroomService = serviceFactory.getShowroomService();
        return controllerExecutor.performAttributeSelect(() -> {
            List<Showroom> showrooms = showroomService.findAll();
            return checkShowroomListPresence(request, showrooms);
        });
    }
}
