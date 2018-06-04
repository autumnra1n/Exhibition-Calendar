package com.company.exhibitions.web.controller.validation.application;

import com.company.exhibitions.dto.User;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.validation.AdminCredentials;
import com.company.exhibitions.web.controller.validation.ControllerValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class AdminCredentialsValidator {

    public boolean validateAdminCredentials(Command command, HttpServletRequest request){
        if(Objects.nonNull(command)&&command.getClass().isAnnotationPresent(AdminCredentials.class)){
            User user = (User) request.getSession().getAttribute("user");
            return Objects.nonNull(user) && user.getRole().getName().equals("admin");
        }
        return true;
    }
}
