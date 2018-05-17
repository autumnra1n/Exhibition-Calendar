package com.company.exhibitions.web.command.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class ShowroomCommand extends Command {

    public String checkShowroomPresence(HttpServletRequest request, Showroom showroom){
        if(Objects.isNull(showroom)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_SHOWROOM.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.showroom");
        }
        else {
            request.setAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase(), showroom);
            return PageManager.getProperty("page.showroom");
        }
    }

    public String checkShowroomListPresence(HttpServletRequest request, List<Showroom> showrooms){
        if(showrooms.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_SHOWROOMS.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.showroom");
        }
        else {
            request.setAttribute(CustomRequestAttributes.SHOWROOMS.name().toLowerCase(), showrooms);
            return PageManager.getProperty("page.showroom");
        }
    }
}
