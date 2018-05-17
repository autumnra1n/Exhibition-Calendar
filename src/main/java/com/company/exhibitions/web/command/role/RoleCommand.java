package com.company.exhibitions.web.command.role;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class RoleCommand extends Command{

    public String checkRolePresence(HttpServletRequest request, Role role){
        if(Objects.isNull(role)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_ROLE.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.role");
        }
        else {
            request.setAttribute(CustomRequestAttributes.ROLE.name().toLowerCase(), role);
            return PageManager.getProperty("page.role");
        }
    }

    public String checkRoleListPresence(HttpServletRequest request, List<Role> roles){
        if(roles.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_ROLES.name().toLowerCase(), new Object());
            return PageManager.getProperty("page.role");
        }
        else {
            request.setAttribute(CustomRequestAttributes.ROLES.name().toLowerCase(), roles);
            return PageManager.getProperty("page.role");
        }
    }
}
