package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BootStrap.class);
        builder.run(args);
    }
}
