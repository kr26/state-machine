package com.example.state.machine.state_machine.status.impl;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;
import com.example.state.machine.state_machine.status.SecondStateService;
import org.springframework.stereotype.Service;

@Service
public class ActionForStateSecondServiceImpl implements SecondStateService {
    private final States status = States.STATE_2;
    private final Events action = Events.EVENT_2;

    @Override
    public States getStatus() {
        return status;
    }

    @Override
    public Events getAction() {
        return action;
    }

    @Override
    public void doAction() {

    }
}
