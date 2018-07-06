package rechard.learn.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class TranslationService {

	@Autowired
	ResourceBundleMessageSource messageSource;
	
	
	public String getMessage(String code,String[] args,Locale locale){
		return messageSource.getMessage(code, args, locale);
	}
}
