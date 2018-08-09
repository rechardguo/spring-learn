package rechard.learn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import rechard.learn.bean.transaction.MyInterceptor;

@Configuration
@ComponentScan(basePackages="rechard.learn.controller")
public class MyWebConfig extends WebMvcConfigurerAdapter {

	
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/rechard/interceptor");
		    registry.addInterceptor(new LocaleChangeInterceptor());
	        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
	    }
	/** 
	 @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.enableContentNegotiation(new MappingJackson2JsonView());
	        registry.jsp();
	    }
	 
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/").setViewName("home");
	    }*/
}
