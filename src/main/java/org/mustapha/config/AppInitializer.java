        package org.mustapha.config;

        import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

        public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

            @Override
            protected Class<?>[] getRootConfigClasses() {
                return new Class[] { AppConfig.class };
            }

            @Override
            protected Class<?>[] getServletConfigClasses() {
                return new Class[] { WebMvcConfig.class };
            }

            @Override
            protected String[] getServletMappings() {
                return new String[] { "/" }; // All requests starting with /api will be handled by DispatcherServlet
            }
        }