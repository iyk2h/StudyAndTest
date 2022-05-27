package com.example.restapiwithspring.index;

import com.example.restapiwithspring.event.EventController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/api")
    public RepresentationModel index() {
        var index = new RepresentationModel<>();
        index.add(Link.of(String.valueOf(EventController.class)).withRel("events"));
        return index;
    }

}
