package org.mustapha.config;

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

    // Add resource handlers if needed
}