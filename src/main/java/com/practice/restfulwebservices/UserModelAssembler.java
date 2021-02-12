package com.practice.restfulwebservices;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.practice.restfulwebservices.entity.User;
import com.practice.restfulwebservices.restcontroller.UserResource;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel(User entity) {

		return EntityModel.of(entity, linkTo(methodOn(UserResource.class).retrieveUser(entity.getId())).withSelfRel(),
				linkTo(methodOn(UserResource.class).retrieveAllUser()).withRel("users"));

	}

}
