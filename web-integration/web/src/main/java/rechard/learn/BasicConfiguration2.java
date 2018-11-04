package rechard.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
//@Import(QualifierConfiguration.class)
public class BasicConfiguration {
	@Bean
	public String basicBean(){
		return new String("helle basic bean");
	}
}
