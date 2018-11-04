package rechard.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(QualifierConfiguration.class)
public class BasicConfiguration2 {
}
