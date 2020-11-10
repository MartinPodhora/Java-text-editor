package com.myapp.service;


import com.myapp.TEException.TEException;
import com.myapp.dao.TeUserPersistence;
import com.myapp.model.TeUser;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/TeUser")
@Tag(name = "Text editor users data", description = "Operations related to text editor users management")
@Consumes(MediaType.APPLICATION_JSON)
public class TeUserService {

    @Inject
    private TeUserPersistence dao;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get list of all users in Text editor")
    public List<TeUser> getAllUsers() throws TEException {
        return dao.retrieveAllUsers();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a user by username")
    public TeUser getUser(@PathParam("username") String username) throws TEException {
        return dao.findUser(username);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "get user if login was successful")
    public TeUser loginUser(TeUser user) throws TEException {
        return dao.logIn(user);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new text editor user")
    public TeUser createUser(TeUser user) throws TEException {
        return dao.persistUser(user);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a text editor user")
    public TeUser updateUser(TeUser user) throws TEException {
        return dao.updateUser(user);
    }

    @DELETE
    @Path("/delete/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete text editor user")
    public TeUser deleteUser(@PathParam("username") String username) throws TEException {
        return dao.deleteUser(username);
    }
}
