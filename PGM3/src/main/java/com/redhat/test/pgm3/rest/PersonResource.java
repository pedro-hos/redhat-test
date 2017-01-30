package com.redhat.test.pgm3.rest;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.redhat.test.pgm3.model.Person;
import com.redhat.test.pgm3.service.impl.PersonService;

@Stateless
@Path("person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	@Inject
	private PersonService personService;
	
	@POST
	public Response create(Person person) {
		
		personService.save(person);
		
		return Response.created(UriBuilder.fromResource(PersonResource.class)
										  .path(person.getId().toString())
										  .build())
					   .build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		
		Person person = personService.findById(id);
		
		if(Objects.isNull(person)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(person).build();
	}

}
