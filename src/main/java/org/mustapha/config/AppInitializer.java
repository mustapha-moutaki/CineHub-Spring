// ─────────────────────────────────────────────────────────────
// AppInitializer.java
// ─────────────────────────────────────────────────────────────
// This class implements Spring's WebApplicationInitializer.
// It is the **entry point** of the web application when deployed to a servlet container (like Tomcat).
// Why we need it:
// - Replaces the traditional web.xml in modern Spring MVC projects.
// - Configures the DispatcherServlet and the application context programmatically.
// - Ensures that Spring MVC starts when the web application is deployed.
// How we use it:
// - Place it in a package scanned by your build tool (e.g., org.mustapha.config).
// - The servlet container automatically detects this class and initializes the Spring context.




package org.mustapha.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ AppConfig.class }; // Services, Repositories, DataSource
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebMvcConfig.class }; // Controllers, REST config
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" }; // all request pass via dispatcher
    }
}
