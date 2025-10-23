package org.mustapha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Configuration
@EnableWebMvc
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public AppInitializer() {
        System.out.println("=== AppInitializer Constructor Called ===");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("=== getRootConfigClasses() called - Loading AppConfig ===");
        return new Class[]{ AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("=== getServletConfigClasses() called - Loading WebMvcConfig ===");
        return new Class[]{ WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("=== getServletMappings() called - Mapping to '/' ===");
        return new String[]{ "/" };
    }
}