package com.company.exhibitions.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class PaginationUtil {

    public void doPagination(Map<String, String> parameters, HttpServletRequest request, Integer rows) {
        if(Objects.nonNull(rows)) {
            int limit = Integer.valueOf(parameters.get("limit"));
            int numberOfPages = rows / limit;

            if (numberOfPages % limit > 0) {
                numberOfPages++;
            }
            request.setAttribute("noOfPages", numberOfPages);
            request.setAttribute("currentPage", Integer.valueOf(parameters.get("currentPage")));
            request.setAttribute("limit", limit);
        }
    }
}
