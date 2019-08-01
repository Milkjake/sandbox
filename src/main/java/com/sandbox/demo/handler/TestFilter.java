package com.sandbox.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Test Filter");
        chain.doFilter(request, response);
    }
}
