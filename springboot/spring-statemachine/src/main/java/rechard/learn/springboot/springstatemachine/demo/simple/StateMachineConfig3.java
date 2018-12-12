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
@EnableStateMachine(name="stateMachine3")
public class StateMachineConfig3 extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
                .withConfiguration()
                .machineId("simpleflow3")
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
                .state("S2")
                .and()
                .withStates()
                .parent("S2")
                .initial("S21")
                .state("S22");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions
                .withExternal()
                .source("S1").target("S2")
                .event("E1")
                .and()
                .withExternal()
                .source("S22").target("S2EXIT")
                .event("EXIT")
                .and()
                .withEntry()
                .source("S2ENTRY").target("S22")
                .and()
                .withExit()
                .source("S2EXIT").target("S3");
    }

    public StateMachineListener<String, String> listener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
