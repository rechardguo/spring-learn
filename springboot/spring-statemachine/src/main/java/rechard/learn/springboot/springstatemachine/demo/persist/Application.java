package rechard.learn.springboot.springstatemachine.demo.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import rechard.learn.springboot.springstatemachine.demo.simple.Bootstrap;

@SpringBootApplication
public class Application  {

    @Configuration
    @EnableStateMachine
    static class StateMachineConfig
            extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineStateConfigurer<String, String> states)
                throws Exception {
            states
                    .withStates()
                    .initial("PLACED")
                    .state("PROCESSING")
                    .state("SENT")
                    .state("DELIVERED");
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source("PLACED").target("PROCESSING")
                    .event("PROCESS")
                    .and()
                    .withExternal()
                    .source("PROCESSING").target("SENT")
                    .event("SEND")
                    .and()
                    .withExternal()
                    .source("SENT").target("DELIVERED")
                    .event("DELIVER");
        }

    }

    @Configuration
    static class PersistHandlerConfig {

        @Autowired
        private StateMachine<String, String> stateMachine;

        @Bean
        public PersistStateMachineHandler persistStateMachineHandler() {
            return new PersistStateMachineHandler(stateMachine);
        }

        @Bean
        public Persist persist() {
            return new Persist(persistStateMachineHandler());
        }
    }
    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.run(args);
    }

}
