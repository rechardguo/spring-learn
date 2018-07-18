package rechard.learn;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

public class LearnProjectWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MyWebConfig.class ,MQConfiguration.class,MQ2Configuration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    //AbstractDispatcherServletInitializer ÀïµÄ¹ýÂËÆ÷
    protected Filter[] getServletFilters() {
        return new Filter[] {
            new HiddenHttpMethodFilter(),
            new CharacterEncodingFilter() 
        };
    }
    
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Optionally also set maxFileSize, maxRequestSize, fileSizeThreshold
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
    }
}