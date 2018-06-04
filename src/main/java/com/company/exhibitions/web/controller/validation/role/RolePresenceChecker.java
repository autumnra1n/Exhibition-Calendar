package com.company.exhibitions.web.controller.validation.role;

import com.company.exhibitions.dto.Role;
import com.company.exhibitions.web.controller.validation.CustomRequestAttributes;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class RolePresenceChecker {

    public String checkRolePresence(HttpServletRequest request, Role role, String successPage, String failurePage){
        if(Objects.isNull(role)){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_ROLE.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.ROLE.name().toLowerCase(), role);
            return successPage;
        }
    }

    public String checkRoleListPresence(HttpServletRequest request, List<Role> roles, String successPage, String failurePage){
        if(roles.isEmpty()){
            request.setAttribute(CustomRequestAttributes.NO_SUCH_ROLES.name().toLowerCase(), new Object());
            return failurePage;
        }
        else {
            request.setAttribute(CustomRequestAttributes.ROLES.name().toLowerCase(), roles);
            return successPage;
        }
    }
}
