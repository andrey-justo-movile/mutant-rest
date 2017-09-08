package com.justo.mutant.api.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.justo.mutant.api.http.filter.RequestFilter;


@Component
@Order(Integer.MIN_VALUE)
public class FilterConfiguration implements ServletContextInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addFilter("log_filter", new RequestFilter()).addMappingForUrlPatterns(null, false, "/*");
    }

}
