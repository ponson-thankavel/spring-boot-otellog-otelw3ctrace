package com.ponson.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class TransactionFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        log.info("Starting a transaction for req: {}", req.getRequestURI());

        Enumeration<String> headers = req.getHeaderNames();
        while(headers.hasMoreElements()){
            String header = headers.nextElement();
            log.info(String.format("Header: %s; Value: %s", header, req.getHeader(header)));
        }

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("Committing a transaction for req: {}", req.getRequestURI());
    }
}
