package com.company.exhibitions.web.controller.validation.showroom;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowroomPresenceChecker {

    public String checkShowroomPresence(HttpServletRequest request, Showroom showroom, String successPage, String failurePage){
        if(Objects.isNull(showroom)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_SHOWROOM.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.SHOWROOM.name().toLowerCase(), showroom);
            return successPage;
        }
    }

    public String checkShowroomListPresence(HttpServletRequest request, List<Showroom> showrooms, String successPage, String failurePage){
        if(showrooms.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_SHOWROOMS.name().toLowerCase(), new Object());
            return successPage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.SHOWROOMS.name().toLowerCase(), showrooms);
            return failurePage;
        }
    }
}
