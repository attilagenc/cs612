package edu.herguan.cs612.termproject.loadgenerator;


import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class LoadGeneratorRestService {
	
	LoadGenerator loadGenerator = new LoadGenerator();
	String serviceUrl = "rest/static/items";

	@Context
	UriInfo uri;
	
	@GET
	@Path("/generateLoad")
	@Produces(MediaType.TEXT_PLAIN)
	public Response generateLoadtoStaticRESTService(@DefaultValue("1") @QueryParam("t") Integer threadCount,
											@DefaultValue("1000") @QueryParam("d") Long delay,
											@DefaultValue("61") @QueryParam("f") Long duration) {
		StringBuilder sb = new StringBuilder();		
		String url = uri.getBaseUri()+serviceUrl;
		sb.append("Will generate 1 request to \""+url+"\" in "+delay+" msecs, for "+duration+" seconds in each of "+threadCount+" threads.");
		long finishTime = System.currentTimeMillis()+duration*1000;
		loadGenerator.generateLoad(threadCount, delay, finishTime, url);
		sb.append("\r\nLoad generation started at: "+new Date(System.currentTimeMillis())+" and will finish at: "+new Date(finishTime));
		return Response.status(200).entity(sb.toString()).build();
	}
	
	@GET
	@Path("/generateLoadd")
	@Produces(MediaType.TEXT_PLAIN)
	public Response generateLoadRESTService(@DefaultValue("1") @QueryParam("t") Integer threadCount,
											@DefaultValue("1000") @QueryParam("d") Long delay,
											@DefaultValue("61") @QueryParam("f") Long duration) {
		StringBuilder sb = new StringBuilder();		
		String url = uri.getBaseUri()+"items";
		sb.append("Will generate 1 request to \""+url+"\" in "+delay+" msecs, for "+duration+" seconds in each of "+threadCount+" threads.");
		long finishTime = System.currentTimeMillis()+duration*1000;
		loadGenerator.generateLoad(threadCount, delay, finishTime, url);
		sb.append("\r\nLoad generation started at: "+new Date(System.currentTimeMillis())+" and will finish at: "+new Date(finishTime));
		return Response.status(200).entity(sb.toString()).build();
	}
}
