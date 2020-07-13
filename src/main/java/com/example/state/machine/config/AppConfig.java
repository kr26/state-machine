package com.example.state.machine.config;

import com.example.state.machine.enums.Events;
import com.example.state.machine.state_machine.status.StatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<Events, StatusService> getStatusImplementationMap(List<StatusService> statusImplementations) {
        Map<Events, StatusService> strategyMap = new HashMap<>();
        for (StatusService implementation : statusImplementations) {
            strategyMap.put(implementation.getAction(), implementation);
        }
        return strategyMap;
    }

}