package com.company.exhibitions.web.command.showroomcommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAllShowroomsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IShowroomService showroomService = serviceFactory.getShowroomService();
        try {
            request.setAttribute("showrooms", showroomService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.showroom");
    }
}
