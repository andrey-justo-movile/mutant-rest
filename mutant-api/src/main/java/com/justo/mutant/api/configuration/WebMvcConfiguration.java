package com.justo.mutant.api.configuration;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptorAdapter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justo.mutant.parser.JacksonMapper;


@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
        configurer.registerDeferredResultInterceptors(new DeferredResultProcessingInterceptorAdapter() {

            @Override
            public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> result) {
                result.setErrorResult(new ServiceUnavailableException());
                return false;
            }
        });
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(false).favorParameter(false).favorPathExtension(true).useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        ObjectMapper objMapper = JacksonMapper.standardConfiguration(new ObjectMapper());
        objMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        converters.add(new MappingJackson2HttpMessageConverter(objMapper));
    }

}
