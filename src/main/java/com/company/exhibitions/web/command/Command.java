package com.company.exhibitions.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    public Map<String, String> extractParameters(HttpServletRequest request){
        Map<String, String> requestParameters = new HashMap<>();

        List<String> parameters = Collections.list(request.getParameterNames());

        for (String param : parameters) {
            requestParameters.put(param, request.getParameter(param));
        }

        return requestParameters;
    }


}
