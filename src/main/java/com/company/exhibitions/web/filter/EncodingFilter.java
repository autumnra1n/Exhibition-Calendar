package com.company.exhibitions.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

public class EncodingFilter implements Filter {

    private String code;

    public void init(FilterConfig fConfig)  {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        System.out.println(codeRequest);

        if (Objects.nonNull(code) && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        code = null;
    }
}
