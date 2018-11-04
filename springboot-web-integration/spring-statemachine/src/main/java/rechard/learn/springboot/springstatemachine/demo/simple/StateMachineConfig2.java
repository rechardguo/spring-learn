package rechard.learn.springboot.springstatemachine.demo.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine(name="stateMachine2")
public class StateMachineConfig2 extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
                .withConfiguration()
                .machineId("simpleflow2")
                .autoStartup(false)
                .listener(listener());
    }


    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial("S1")
                .end("SF")
                .states(new HashSet<String>(Arrays.asList("S1","S2","S3","S4")));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions.withExternal().source("S1").target("S2").event("S1-S2").action((context)->{
            System.out.println("S1-S2 event");
        }).and().withExternal().source("s2").target("s3").event("S2-S3").action((context)->{
            System.out.println("S2-S3 event");
        }).and().withExternal().source("s3").target("s4").event("S3-S4").action((context)->{
            System.out.println("S3-S4 event");
        });
    }

    @Bean
    public StateMachineListener<String, String> listener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
