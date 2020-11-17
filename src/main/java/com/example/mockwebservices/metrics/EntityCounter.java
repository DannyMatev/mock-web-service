package com.example.mockwebservices.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class EntityCounter {

    private final Counter counter;

    public EntityCounter(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("created.entities");
    }

    public void handleCreatedEntity() {
        this.counter.increment();
    }
}
