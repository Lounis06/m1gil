package urouen.sepa.configuration;

import org.springframework.web.servlet.support.
AbstractAnnotationConfigDispatcherServletInitializer;
import urouen.sepa.configuration.AppConfig;

public class ServletInitializer extends 
AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SepaConfiguration.class };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
