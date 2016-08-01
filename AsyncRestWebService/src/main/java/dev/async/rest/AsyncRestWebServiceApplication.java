package dev.async.rest;

import javax.inject.Named;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.async.rest.services.AsyncService;

@SpringBootApplication
public class AsyncRestWebServiceApplication {

    @Named
    public static class JerseyConfig extends ResourceConfig {

        public JerseyConfig() {
            this.register(AsyncService.class);
            this.register(JacksonFeature.class);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncRestWebServiceApplication.class, args);
    }
}