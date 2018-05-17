package com.company.exhibitions.web.command.usercommand;

import com.company.exhibitions.dto.Ticket;
import com.company.exhibitions.dto.User;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class UserCommand extends Command {

    public String checkUserPresence(HttpServletRequest request, User user){
        if(Objects.isNull(user)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_USER.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.user");
        }
        else {
            request.setAttribute(CustomRequestAttributes.USER.name().toLowerCase(), user);
            return PageManager.getProperty("page.user");
        }
    }

    public String checkUserListPresence(HttpServletRequest request, List<User> users){
        if(users.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_USERS.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.user");
        }
        else {
            request.setAttribute(CustomRequestAttributes.USERS.name().toLowerCase(), users);
            return PageManager.getProperty("page.user");
        }
    }
}
