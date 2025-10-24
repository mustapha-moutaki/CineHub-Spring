package org.mustapha.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.mustapha.controller",
        "org.mustapha.service",
        "org.mustapha.repository",
        "org.mustapha.mapper"
})
public class WebMvcConfig implements WebMvcConfigurer {

    public WebMvcConfig() {
        System.out.println("=== WebMvcConfig Constructor Called ===");
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
    // Add resource handlers if needed
}