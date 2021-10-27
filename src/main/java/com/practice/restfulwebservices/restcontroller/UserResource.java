package com.practice.restfulwebservices.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.restfulwebservices.UserModelAssembler;
import com.practice.restfulwebservices.entity.User;
import com.practice.restfulwebservices.exception.UserNotFoundException;
import com.practice.restfulwebservices.service.UserDaoService;

import io.swagger.annotations.Api;

@RestController
@Api("restful-web-services")
public class UserResource {

	private UserDaoService userDaoService;
	private Logger logger=Logger.getLogger(UserResource.class);
	
	private UserModelAssembler userModelAssembler;

	public UserResource(UserDaoService userDaoService, UserModelAssembler userModelAssembler) {
		this.userDaoService = userDaoService;
		this.userModelAssembler = userModelAssembler;

	}

	// retrieveAllUsers

	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> retrieveAllUser() {
		
		logger.trace("get all users using this endpoint");

		/*
		 * List<EntityModel<User>> users = userDaoService.findAll().stream() .map(user
		 * -> EntityModel.of(user,
		 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		 * retrieveUser(user.getId())) .withSelfRel(),
		 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		 * retrieveAllUser()) .withRel("users"))) .collect(Collectors.toList());
		 */

		List<EntityModel<User>> users = userDaoService.findAll().stream().map(userModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(this.getClass()).retrieveAllUser()).withSelfRel());

	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {

		User user = Optional.ofNullable(userDaoService.findOne(id))
				.orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));

		/*
		 * return EntityModel.of(user,
		 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		 * retrieveUser(id)).withSelfRel(),
		 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
		 * retrieveAllUser()) .withRel("users"));
		 */
		return userModelAssembler.toModel(user);

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {

		Optional.ofNullable(userDaoService.deleteById(id))
				.orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));

	}

}
