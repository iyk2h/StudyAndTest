package com.example.restapiwithspring.event;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//    방법 1
//public class EventResource extends RepresentationModel {
//
//    @JsonUnwrapped
//    private Event event;
//
//    public EventResource(Event event) {
//        this.event = event;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//}

// 방법 2

public class EventResource extends EntityModel<Event> {
    public EventResource(Event event, Link... links) {
        super(event, Arrays.asList(links));
        Link link = linkTo(EventController.class).slash(event.getId()).withSelfRel();
        add(link);
        add(linkTo(EventController.class).withRel("query-events"));
    }
}