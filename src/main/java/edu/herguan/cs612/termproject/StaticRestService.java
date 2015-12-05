package edu.herguan.cs612.termproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.herguan.cs612.termproject.resource.ResourceLoader;

@Path("/static")
public class StaticRestService implements ItemCRUDServiceInterface {
		/* (non-Javadoc)
		 * @see edu.herguan.cs612.termproject.ItemCRUDServiceInterface#itemList()
		 */
		@Override
		@GET
		@Path("/items")
		//@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response itemList() {
			
			StringBuilder responseBuilder = ResourceLoader.loadItemList();
			//System.out.println("Data Received: " + responseBuilder.toString());
			
			// return HTTP response 200 in case of success
			return Response.status(200).entity(responseBuilder.toString()).build();
		}

		/* (non-Javadoc)
		 * @see edu.herguan.cs612.termproject.ItemCRUDServiceInterface#itemDetail(java.lang.String)
		 */
		@Override
		@GET
		@Path("/item/{item}")
		//@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response itemDetail(@PathParam("item") String itemId) {
			StringBuilder responseBuilder = ResourceLoader.loadItemDetail(itemId);
			//System.out.println("Data Received: " + responseBuilder.toString());
			
			// return HTTP response 200 in case of success
			return Response.status(200).entity(responseBuilder.toString()).build();
		}
		
		/* (non-Javadoc)
		 * @see edu.herguan.cs612.termproject.ItemCRUDServiceInterface#verifyRESTService()
		 */
		@Override
		@GET
		@Path("/verify")
		@Produces(MediaType.TEXT_PLAIN)
		public Response verifyRESTService() {
			String result = "Application(Static Service) Successfully started..";	 
			// return HTTP response 200 in case of success
			return Response.status(200).entity(result).build();
		}
}
