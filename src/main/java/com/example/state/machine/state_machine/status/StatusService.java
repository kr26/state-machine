package com.example.state.machine.state_machine.status;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;

public interface StatusService {

    States getStatus();
    Events getAction();

    void doAction();

}
