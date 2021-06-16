package com.ponson.actuator;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.MDC;

@Component
public class MDCFilter implements Filter {   //http://logback.qos.ch/manual/mdc.html

    private static final String MDC_OTEL_REMOTE_HOST_IP = "net.peer.ip";
    private static final String MDC_OTEL_HTTP_METHOD = "http.method";
    private static final String MDC_OTEL_HTTP_URL = "http.url";
    private static final String MDC_OTEL_HTTP_USER_AGENT = "http.user_agent";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        setMDC(httpRequest);

        chain.doFilter(request, response);
    }

    private void setMDC(HttpServletRequest httpRequest){
        String remoteHost = httpRequest.getRemoteHost();
        String httpMethod = httpRequest.getMethod();
        String httpRequestURL = httpRequest.getRequestURL().toString();
        String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);

        MDC.put(MDC_OTEL_HTTP_METHOD,httpMethod);
        MDC.put(MDC_OTEL_HTTP_URL,httpRequestURL);
        MDC.put(MDC_OTEL_HTTP_USER_AGENT,userAgent);
        MDC.put(MDC_OTEL_REMOTE_HOST_IP,remoteHost);
    }
}
