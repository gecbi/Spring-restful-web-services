package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	//spring-web-services/restful-web-services/backup04-basic-create-service.md
	
	
	@Autowired
	private UserDaoService service;
	//private User saveUser;

	//GET /users
	//retriveAllUsers 
	@GetMapping("/users")
	public List<User> retriveAllUsers ()
	{
		return service.findAll();
	}
	
	//GET /user/{id}
	//retriveUser (int id)
	@GetMapping(path="/users/{id}")
	public Resource<User> retriveUser (@PathVariable int id) 
	{
		User user = service.findOne(id);
		if(user==null) 
		{
			throw new UserNotFoundException("id- "+ id );
		}
		
		//How to make a link to other method on hte page = HATEOAS
		//"all-user", SERVER_PATH + "/users" 
		//retriveAllusers
		
		//Make a resource concept 
		Resource<User> resource = new Resource<User>(user);
		//import all static methods of ControllerLinkBuilder 
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) 
	{
		User user = service.deleteById(id);
		
		if(user==null) 
		{
			throw new UserNotFoundException("id- "+ id );
		}
		
		
	}
	
	
	//Created 
	// details of the user 
	//output - CREATED & Return the created URI
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) 
	{
		User savedUser = service.save(user);
		//CREATED
		// /user/{id}	savedUser.getId()
		URI location = ServletUriComponentsBuilder.
		fromCurrentRequest().
		path("/{id}").
		buildAndExpand(savedUser.getId()).toUri();
				
		return ResponseEntity.created(location).build();
	}
	
}
