package com.example.state.machine.state_machine.status.impl;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;
import com.example.state.machine.state_machine.status.FirstStateService;
import com.example.state.machine.state_machine.status.SecondStateService;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;

@Service
public class ActionForStateFirstServiceImpl implements FirstStateService, SecondStateService {
    private final States status = States.STATE_1;
    private final Events action = Events.EVENT_1;

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
