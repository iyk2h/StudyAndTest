package com.example.restapiwithspring.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    void builder() {
        Event event = Event.builder()
                .name("Spring Rest API")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "Spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);

    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "100, 0, false",
            "0, 100, false"
    })
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        //Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        // When
        event.update();

        // Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    @ParameterizedTest
    @MethodSource("isOffline")
    public void testOffline(String location, boolean isOffline) {
        //Given
        Event event = Event.builder()
                .location(location)
                .build();
        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private static Stream<Arguments> isOffline() {
        return Stream.of(
                Arguments.of("강남역", true),
                Arguments.of(null, false),
                Arguments.of("", false)
        );
    }
}