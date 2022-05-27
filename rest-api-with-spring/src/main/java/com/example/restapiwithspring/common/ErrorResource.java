package com.example.restapiwithspring.common;

import com.example.restapiwithspring.index.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorResource extends EntityModel<Errors>{
    public ErrorResource (Errors error, Link... links) {
        super(error, Arrays.asList(links));
        Link link = linkTo(methodOn(IndexController.class).index()).withRel("index");
        add(link);
    }
}