package com.dayuanit.emall.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class CharFilter implements Filter{

    private static String charEncoding;

    Logger log = LoggerFactory.getLogger(CharFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>>filterInit");
        charEncoding = filterConfig.getInitParameter("charEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(charEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info(">>>filterDestory");
    }
}
