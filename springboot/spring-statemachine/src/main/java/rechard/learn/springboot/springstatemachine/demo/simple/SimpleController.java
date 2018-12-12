package rechard.learn.springboot.springstatemachine.demo.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.support.DefaultStateContext;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SimpleController {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    @Qualifier("stateMachine2")
    private StateMachine stateMachine2;

    @Autowired
    @Qualifier("stateMachine3")
    private StateMachine stateMachine3;

    @RequestMapping("/step2")
    @ResponseBody
    public String step2() throws Exception {
        stateMachine.sendEvent(Events.E2);
        return "ok";
    }

    @RequestMapping("/step1")
    @ResponseBody
    public String step1() throws Exception {
        Message<Events> message = MessageBuilder
                .withPayload(Events.E1)
                .setHeader("foo", "bar")
                .build();
        stateMachine.sendEvent(message);
        return "ok";
    }


    @RequestMapping("/start")
    @ResponseBody
    public String start() throws Exception {
        stateMachine.stop();
        stateMachine.start();
        return "ok";
    }

    @RequestMapping("/start2")
    @ResponseBody
    public String start2() throws Exception {
        stateMachine2.start();
        Message<String> msg = MessageBuilder
                .withPayload("S1-S2")
                .setHeader("foo", "bar")
                .build();
        stateMachine2.sendEvent(msg);
        return "ok";
    }

    @RequestMapping("/sc3")
    @ResponseBody
    public String sc3() throws Exception {
        stateMachine3.start();
        stateMachine3.sendEvent(MessageBuilder
                .withPayload("E1")
                .setHeader("foo", "bar")
                .build());
        return "ok";
    }

    @RequestMapping("/handleEventWithState/{state}/{event}")
    @ResponseBody
    public boolean handleEventWithState(@PathVariable("state") String state,
                                        @PathVariable("event") String event) {
        Message<String> eventMsg = MessageBuilder
                .withPayload("event")
                .setHeader("foo", "bar")
                .build();

        stateMachine2.stop();
        List<StateMachineAccess<String, String>> withAllRegions = stateMachine2.getStateMachineAccessor()
                .withAllRegions();
        for (StateMachineAccess<String, String> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null));
        }
        stateMachine2.start();
        return stateMachine2.sendEvent(eventMsg);
    }

}
