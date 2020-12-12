package com.myapp.TEException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {

        if (ex instanceof WebApplicationException) {
            return ((WebApplicationException) ex).getResponse();
        }

        String msg = ex.getMessage();
        if ( msg == null) {
            msg = ex.getClass().getName();
        }

        return Response.status(500)
                .header("message", msg)
                .build();
    }
}
