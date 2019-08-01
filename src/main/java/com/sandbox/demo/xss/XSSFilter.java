package com.sandbox.demo.xss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class XSSFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XSSFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("XSS Filter");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

}
