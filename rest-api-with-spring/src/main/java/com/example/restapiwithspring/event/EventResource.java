package com.example.restapiwithspring.event;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//    방법 1
public class EventResource extends RepresentationModel {

    @JsonUnwrapped
    private Event event;

//    public EventResource(Event event) {
//        this.event = event;
//    }

    public EventResource(Event event) {
        this.event = event;
        this.add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
        this.add(linkTo(EventController.class).withRel("query-events"));
        this.add(Link.of("/docs/index.html#resources-events-create").withRel("profile"));
    }

    public Event getEvent() {
        return event;
    }
}

// 방법 2
//
//public class EventResource extends EntityModel<Event> {
//    public EventResource(Event event, Link... links) {
//        super(event, Arrays.asList(links));
//        Link link = linkTo(EventController.class).slash(event.getId()).withSelfRel();
//        add(link);
//        add(linkTo(EventController.class).withRel("query-events"));
//    }
//}