package com.example.state.machine.config;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;
import java.util.Optional;

@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    private static final String EVENT_ACTION_MESSAGE = "[EVENT] SOME ACTION COULD BE EXECUTED HERE FOR EVENT: ";

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.NO_STATE)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.NO_STATE).target(States.STATE_1)
                .event(Events.EVENT_1)
                .action(firstEventAction())
                .and()
                .withExternal()
                .source(States.STATE_1).target(States.STATE_2)
                .event(Events.EVENT_2)
                .action(secondEventAction())
                .and()
                .withExternal()
                .source(States.STATE_2).target(States.STATE_1)
                .event(Events.EVENT_3)
                .action(thirdEventAction());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .listener(getListener())
                .autoStartup(true);
    }

    private StateMachineListener<States, Events> getListener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                log.info("[STATE] State moved from: {} to: {}",
                        ofNullableState(transition.getSource()),
                        ofNullableState(transition.getTarget()));
            }

            @Override
            public void eventNotAccepted(Message<Events> event) {
                log.warn("[WARN] Event not accepted: " + event);
            }

            private Object ofNullableState(State s) {
                return Optional.ofNullable(s)
                        .map(State::getId)
                        .orElse(null);
            }
        };
    }

    private Action<States, Events> firstEventAction() {
        return context -> log.info(EVENT_ACTION_MESSAGE + context.getEvent());
    }

    private Action<States, Events> secondEventAction() {
        return context -> log.info(EVENT_ACTION_MESSAGE + context.getEvent());
    }

    private Action<States, Events> thirdEventAction() {
        return context -> log.info(EVENT_ACTION_MESSAGE + context.getEvent());
    }
}
