package com.example.state.machine.service.impl;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;
import com.example.state.machine.service.StateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateMachine<States, Events> stateMachine;

    @Override
    public String doSignals(States state) {
        switch (state) {
            case STATE_1:
                log.info("[EVENT] Sent EVENT_1");
                if (!States.NO_STATE.equals(stateMachine.getState().getId())) {
                    stateMachine.sendEvent(Events.EVENT_3);
                    break;
                }
                stateMachine.sendEvent(Events.EVENT_1);
                break;
            case STATE_2:
                log.info("[EVENT] Sent EVENT_2");
                stateMachine.sendEvent(Events.EVENT_2);
                break;
            default:
                break;
        }
        String resultState = stateMachine.getState().getId().toString();
        log.info("[RESULT] STATE MACHINE State: {}", resultState);
        return resultState;
    }

}
