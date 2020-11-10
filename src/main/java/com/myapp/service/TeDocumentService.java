package com.myapp.service;

import com.myapp.TEException.TEException;
import com.myapp.dao.TeDocumentPersistence;
import com.myapp.model.TeDocument;
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
@Path("/TeDocument")
@Tag(name = "Text editor documents data", description = "Operations related to text editor documents management")
@Consumes(MediaType.APPLICATION_JSON)
public class TeDocumentService {

    @Inject
    private TeDocumentPersistence dao;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a card")
    public TeDocument addDocument(TeDocument doc) throws TEException {
        return dao.addDocument(doc);
    }

    @GET
    @Path("/all/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all documents for user")
    public List<TeDocument> getAllDoc(@PathParam("username") String username) throws TEException {
        return dao.getAll(username);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update document")
    public TeDocument updateDocument(TeDocument doc) throws TEException {
        return dao.updateDocument(doc);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete document")
    public Long deleteDocument(@PathParam("id") Long id) throws TEException {
        return dao.deleteDocument(id);
    }
}
