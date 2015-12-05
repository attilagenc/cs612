package edu.herguan.cs612.termproject;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HealthRestService {
	static boolean healthy = true;
	
		@GET
		@Path("/health")
		@Produces(MediaType.TEXT_PLAIN)
		public Response generateLoadRESTService() {
			// return HTTP response 200 in case of success
			if (healthy)
				return Response.status(200).entity("OK").build();
			else
				return Response.status(503).entity("Service Disabled...").build();
		}
		
		@GET
		@Path("/health/disable")
		@Produces(MediaType.TEXT_PLAIN)
		public Response disableHealthService() {
			healthy = false;
			return Response.status(200).entity("ACK..").build();
		}
		
		@GET
		@Path("/health/enable")
		@Produces(MediaType.TEXT_PLAIN)
		public Response enableHealthService() {
			healthy = true;
			return Response.status(200).entity("ACK...").build();
		}
}
