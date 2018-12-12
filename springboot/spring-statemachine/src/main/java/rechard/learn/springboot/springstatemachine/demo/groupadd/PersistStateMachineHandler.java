package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class PersistStateMachineHandler extends LifecycleObjectSupport {

    private final StateMachine<Status,ActionType> stateMachine;
    private final PersistingStateChangeInterceptor interceptor = new PersistingStateChangeInterceptor();
    private final CompositePersistStateChangeListener listeners = new CompositePersistStateChangeListener();
    /**
     * Instantiates a new persist state machine handler.
     *
     * @param stateMachine the state machine
     */
    @Autowired
    public PersistStateMachineHandler(StateMachine<Status,ActionType> stateMachine) {
        Assert.notNull(stateMachine, "State machine must be set");
        this.stateMachine = stateMachine;
    }

    @Override
    protected void onInit() throws Exception {
        stateMachine.getStateMachineAccessor().doWithAllRegions(new StateMachineFunction<StateMachineAccess<Status,ActionType>>() {
            public void apply(StateMachineAccess<Status,ActionType> function) {
                function.addStateMachineInterceptor(interceptor);
            }
        });

        //to add PersistStateChangeListener
        Map<String, PersistStateChangeListener> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) this.getBeanFactory(), PersistStateChangeListener.class, true, false);
        if (!matchingBeans.isEmpty()) {
            listeners.setListeners(new ArrayList(matchingBeans.values()));
        }
    }

    /**
     * Handle event with entity.
     *
     * @param event the event
     * @param state the state
     * @return true if event was accepted
     */
    public boolean handleEventWithState(Message<ActionType> event, Status state) {
        stateMachine.stop();
        List<StateMachineAccess<Status,ActionType>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        for (StateMachineAccess<Status,ActionType> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<Status,ActionType>(state, null, null, null));
        }
        stateMachine.start();
        return stateMachine.sendEvent(event);
    }

    /**
     * Adds the persist state change listener.
     *
     * @param listener the listener
     */
    public void addPersistStateChangeListener(PersistStateChangeListener listener) {
        listeners.register(listener);
    }

    /**
     * The listener interface for receiving persistStateChange events.
     * The class that is interested in processing a persistStateChange
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addPersistStateChangeListener</code> method. When
     * the persistStateChange event occurs, that object's appropriate
     * method is invoked.
     */
    public interface PersistStateChangeListener {
        /**
         * Called when state needs to be persisted.
         *
         * @param state        the state
         * @param message      the message
         * @param transition   the transition
         * @param stateMachine the state machine
         */
        void onPersist(State<Status,ActionType> state, Message<ActionType> message, Transition<Status,ActionType> transition,
                       StateMachine<Status,ActionType> stateMachine);
    }

    private class PersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<Status,ActionType> {
        @Override
        public void preStateChange(State<Status, ActionType> state, Message<ActionType> message, Transition<Status, ActionType> transition, StateMachine<Status, ActionType> stateMachine) {
            listeners.onPersist(state,message,transition,stateMachine);
        }
    }

    private class CompositePersistStateChangeListener extends AbstractCompositeListener<PersistStateChangeListener> implements
            PersistStateChangeListener {
        public void onPersist(State<Status,ActionType> state, Message<ActionType> message,
                              Transition<Status,ActionType> transition, StateMachine<Status,ActionType> stateMachine) {
            for (Iterator<PersistStateChangeListener> iterator = getListeners().reverse(); iterator.hasNext(); ) {
                PersistStateChangeListener listener = iterator.next();
                listener.onPersist(state, message, transition, stateMachine);
            }
        }
    }
}