package com.justo.mutant.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import io.undertow.Undertow.Builder;
import io.undertow.servlet.api.DeploymentInfo;

@Configuration
public class WebServerConfiguration implements EmbeddedServletContainerCustomizer {

    private static final int WORKER_MULTIPLIER = 8;

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        UndertowEmbeddedServletContainerFactory containerFactory = (UndertowEmbeddedServletContainerFactory) container;
        containerFactory.addBuilderCustomizers(new UndertowBuilderCustomizer() {

            @Override
            public void customize(Builder builder) {
                int availableCores = Runtime.getRuntime().availableProcessors();
                builder.setIoThreads(availableCores);
                builder.setWorkerThreads(availableCores * WORKER_MULTIPLIER);
            }
        });

        containerFactory.addDeploymentInfoCustomizers(new UndertowDeploymentInfoCustomizer() {

            @Override
            public void customize(DeploymentInfo deploymentInfo) {
                deploymentInfo.setAllowNonStandardWrappers(true);
            }
        });
    }

}