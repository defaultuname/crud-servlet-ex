package com.product.filter;

import javax.servlet.*;
import java.io.IOException;

/*
    Фильтр, отвечающий за кодировку.
*/

public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }
}