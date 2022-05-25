package com.example.restapiwithspring.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void builder() {
        Event event = Event.builder().build();
        Assertions.assertThat(event).isNotNull();
    }
}