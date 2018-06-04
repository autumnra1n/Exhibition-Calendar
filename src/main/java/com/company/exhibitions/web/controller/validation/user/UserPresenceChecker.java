package com.company.exhibitions.web.controller.validation.user;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class UserPresenceChecker {

    public String checkUserPresence(HttpServletRequest request, User user, String successPage, String failurePage){
        if(Objects.isNull(user)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_USER.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.USER.name().toLowerCase(), user);
            return successPage;
        }
    }

    public String checkUserListPresence(HttpServletRequest request, List<User> users, String successPage, String failurePage){
        if(users.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_USERS.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.USERS.name().toLowerCase(), users);
            return successPage;
        }
    }
}
