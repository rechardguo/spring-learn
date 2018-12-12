package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
public class GroupPersistStateChangeListener  implements PersistStateMachineHandler.PersistStateChangeListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GroupPersistStateChangeListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void onPersist(State<Status, ActionType> state, Message<ActionType> message, Transition<Status, ActionType> transition, StateMachine<Status, ActionType> stateMachine) {
        if (message != null && message.getHeaders().containsKey("group")) {
            Group group = message.getHeaders().get("group", Group.class);
            jdbcTemplate.update("update pagroup set status = ? where groupId = ?", state.getId().getStatusCode(), group.getGroupId());
        }
    }
}
