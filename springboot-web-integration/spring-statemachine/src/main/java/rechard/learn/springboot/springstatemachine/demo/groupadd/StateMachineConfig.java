package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import rechard.learn.springboot.springstatemachine.demo.simple.States;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<Status,ActionType>{


    @Override
    public void configure(StateMachineStateConfigurer<Status,ActionType> states) throws Exception {
        states.withStates()
                .initial(Status.PENDING_APPROVAL)
                .choice(Status.CHOICE)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status,ActionType> transitions) throws Exception {
        transitions
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.PARTIALLY_APPROVED).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.CHOICE).event(ActionType.APPROVE)
                .and()
                .withChoice()
                .source(Status.CHOICE)
                .first(Status.PENGDING_DOCUMENT_CHECK,(context)->{
                    Group group = context.getMessage().getHeaders().get("group",Group.class);
                    return group.isAdvance();
                })
                .last(Status.APPROVED)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_APPROVAL_CONFIRMATION).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.APPROVED).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_REJECT_CONFIRMATION).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.REJECTED).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.REJECTED).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.REJECTED).event(ActionType.REJECT);
    }

}
