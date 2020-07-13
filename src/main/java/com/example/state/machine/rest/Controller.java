package com.example.state.machine.rest;

import com.example.state.machine.enums.Events;
import com.example.state.machine.enums.States;
import com.example.state.machine.service.StateService;
import com.example.state.machine.state_machine.status.SecondStateService;
import com.example.state.machine.state_machine.status.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class Controller {
    private final StateService stateService;
    private final Map<Events, StatusService> statesStatusServiceMap;
    private final List<StatusService> statusImplementations;
    private final List<SecondStateService> secondStatusImplementations;


    @GetMapping("/state/{state}")
    public ResponseEntity<String> testMethodSsr(@PathVariable("state") States state) {
        log.info("[REST] Received request to state: {}", state);
        String resultState = stateService.doSignals(state);
        return ResponseEntity.ok(resultState);
    }

    @GetMapping("/state/test")
    public ResponseEntity<String> testMethod() {
        log.info("[REST] Show map: ");
        for (Map.Entry entry : statesStatusServiceMap.entrySet()) {
            log.info("[MAP] {} | {}", entry.getKey(), (entry.getValue().getClass().getSimpleName()));
        }

        for (StatusService service : statusImplementations) {
            log.info("LIST: {}", service.getClass().getSimpleName());
        }

        for (StatusService service : secondStatusImplementations) {
            log.info("LIST1: {}", service.getClass().getSimpleName());
        }

        return ResponseEntity.ok().build();
    }

}
