package rechard.learn;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rechard.learn.bean.LifeCycleBean;

@Configuration
public class LifeCycleBeanConfiguration {

    @Bean
    public LifeCycleBean lifeCycleBean(){
        return new LifeCycleBean();
    }
}
