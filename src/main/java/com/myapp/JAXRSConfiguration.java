package com.myapp;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/texteditor")
@OpenAPIDefinition(info = @Info(
        title = "Text editor",
        version = "1.0",
        description = "Text editor server"
))
public class JAXRSConfiguration extends Application {
}