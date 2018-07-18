package rechard.learn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages="rechard.learn")
public class MyWebConfig extends WebMvcConfigurerAdapter {

	
	
	/* @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new LocaleChangeInterceptor());
	        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
	    }
	 
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
