package edu.herguan.cs612.termproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/img")
public class ImageServerRestService {
	@GET
	@Path("/phones")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getImage() {
		String result = "Application(Static Service) Successfully started..";	 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
}
