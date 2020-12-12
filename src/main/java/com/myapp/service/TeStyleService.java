package com.myapp.service;


import com.myapp.TEException.TEException;
import com.myapp.dao.TeDocumentPersistence;
import com.myapp.dao.TeRefData;
import com.myapp.model.TeDocument;
import com.myapp.model.TeStyle;
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
@Path("/TeStyle")
@Tag(name = "Text editor styles data", description = "Operations related to text editor styles management")
@Consumes(MediaType.APPLICATION_JSON)
public class TeStyleService {
    @Inject
    private TeRefData dao;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a style")
    public TeStyle addStyle(TeStyle style) throws TEException {
        return dao.createStyle(style);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all styles")
    public List<TeStyle> getAllStyles() {
        return dao.retrieveAllStyles();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update style")
    public TeStyle updateStyle(TeStyle style) throws TEException {
        return dao.updateStyle(style);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete style")
    public Long deleteStyle(@PathParam("id") Long id) throws TEException {
        return dao.deleteStyle(id);
    }

    @GET
    @Path("/all/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get style for user")
    public TeStyle getStyle(@PathParam("username") String username) throws TEException {
        return dao.findStyleForUser(username);
    }
}
