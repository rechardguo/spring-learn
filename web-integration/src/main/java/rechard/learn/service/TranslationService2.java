package rechard.learn.service;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class TranslationService2 {

	static ResourceBundleMessageSource messageSource;
	
	@Autowired
	ResourceBundleMessageSource messageSource2;
	
	
	@PostConstruct
	public void init(){
		this.messageSource=messageSource2;
	}
	
	public static String getMessage(String code,String[] args,Locale locale){
		return messageSource.getMessage(code, args, locale);
	}
}
