package com.example.state.machine.rest;

import com.example.state.machine.enums.States;
import com.example.state.machine.service.StateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class Controller {
    private final StateService stateService;

    @GetMapping("/state/{state}")
    public ResponseEntity<String> testMethodSsr(@PathVariable("state") States state) {
        log.info("[REST] Received request to state: {}", state);
        String resultState = stateService.doSignals(state);
        return ResponseEntity.ok(resultState);
    }

}
