package com.justo.mutant.api.http.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import com.justo.mutant.log.Log;
import com.justo.mutant.log.LogManager;


public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogManager.stamp();
        StopWatch chronometer = new StopWatch();
        chronometer.start();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        StringBuilder sb = new StringBuilder();
        sb.append(httpRequest.getMethod().toUpperCase());
        if (StringUtils.isBlank(httpRequest.getHeader("X-Forwarded-For"))) {
            sb.append(" FROM ").append(request.getRemoteAddr());
        } else {
            sb.append(" FROM ").append(httpRequest.getHeader("X-Forwarded-For"));
        }

        if (StringUtils.isNotBlank(httpRequest.getContentType())) {
            sb.append("\t TYPE : ").append(httpRequest.getContentType());
        }
        if (httpRequest.getContentLength() > 0) {
            sb.append("\t LENGTH: ").append(httpRequest.getContentLength());
        }

        sb.append("\t ").append(httpRequest.getRequestURL().toString());
        if (httpRequest.getQueryString() != null) {
            sb.append("?").append(httpRequest.getQueryString());
        }

        sb.append("\t PARAMETERS [");

        Enumeration<String> parameters = request.getParameterNames();

        while (parameters.hasMoreElements()) {
            String name = (String) parameters.nextElement();
            sb.append(" ").append(name).append(": ").append(request.getParameter(name)).append(";");
        }
        Log.CORE.info("{}", sb.append(" ]").toString());
        Log.CORE.debug("Elapsed time: {} ms", chronometer.getTime());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
