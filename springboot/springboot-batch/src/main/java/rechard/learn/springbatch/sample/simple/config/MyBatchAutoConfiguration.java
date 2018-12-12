package rechard.learn.springbatch.sample.simple.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:batch.properties")
@EnableConfigurationProperties(MyBatchProperties.class)
public class MyBatchAutoConfiguration {

}
