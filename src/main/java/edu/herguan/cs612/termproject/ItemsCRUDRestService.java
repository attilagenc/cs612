package edu.herguan.cs612.termproject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.herguan.cs612.termproject.model.Item;
import edu.herguan.cs612.termproject.model.ItemDetails;
import edu.herguan.cs612.termproject.resource.ResourceLoader;

@Path("/")
public class ItemsCRUDRestService implements ItemCRUDServiceInterface {
	

	@Override
	@GET
	@Path("/items")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response itemList() {
		List<Item> items = list();
        Gson gson = new Gson();
        String response = gson.toJson(items);		
		// return HTTP response 200 in case of success
		return Response.status(200).entity(response).build();
	}

	@Override
	@GET
	@Path("/item/{item}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response itemDetail(@PathParam("item") String item) {
		ItemDetails itemDetails = get(item);
		//System.out.println("Data Received: " + responseBuilder.toString());
		
        Gson gson = new Gson();
        String response = gson.toJson(itemDetails);		
		// return HTTP response 200 in case of success
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/item/{item}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response itemDetailUpdate(@PathParam("item") String item,String data) {
		System.out.println("item=" +item+" data="+data);
		Gson gson = new Gson();
		ItemDetails itd = gson.fromJson(data, ItemDetails.class);
		if (!item.equals(itd.id))
			return Response.status(400).entity("Not saved!!!").build();
		if (itd.sid == null)
			save(itd);
		else
			update(itd);
		// return HTTP response 200 in case of success
		return Response.status(200).entity("ok").build();
	}

	@GET
	@Path("/item-edit/{item}")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response itemDetailEdit(@PathParam("item") String item) {
		if (item != null && item.length()>0){
			ItemDetails itemDetails = get(item);
			if (itemDetails != null){
		        Gson gson = new Gson();
		        String response = gson.toJson(itemDetails);		
				// return HTTP response 200 in case of success
				return Response.status(200).entity(response.toString()).build();			
			}
			return Response.status(200).entity(loadCustomizedItemDetail(item).toString()).build();			
		}
		else
			return Response.status(200).entity(ResourceLoader.loadItemDetail(item).toString()).build();
	}

	private StringBuilder loadCustomizedItemDetail(String item) {
		StringBuilder sb = ResourceLoader.loadItemDetail("sample-item");
		Pattern p = Pattern.compile("sample-id");
		Matcher m = p.matcher(sb.toString());
		 StringBuffer result = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(result, item);
		 }
		 m.appendTail(result);	
		 sb = new StringBuilder(result.toString());
		return sb;
	}
	
	@Override
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService() {
		String result = "Application Successfully started..";	 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/import")
	@Produces(MediaType.TEXT_PLAIN)
	public Response importItems() {
		StringBuilder sb = ResourceLoader.loadItemList();
		Type listType = new TypeToken<ArrayList<Item>>() {
        }.getType();
        Gson gson = new Gson();
		List<Item> items = gson.fromJson(sb.toString(), listType);
        
        EntityManager em = null;
        EntityTransaction tx = null;
        int count = 0;
        try {
            em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            for (Item item : items) { 
            	try {
					em.persist(item);
					ItemDetails itd = gson.fromJson(ResourceLoader.loadItemDetail(item.id).toString(), ItemDetails.class);
					em.persist(itd);
					count++;
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Error in importing item: "+item.id);
				}
    		}
        }
        catch(Exception e){
        	if (tx != null)
        		tx.rollback();
			e.printStackTrace();
        }
        finally {
        	if (tx != null)
                tx.commit();
            if (em!=null) {
                em.close();
            }
        }
        
		return Response.status(200).entity(+count+" items of loaded "+items.size()+" items imported.").build();
	}
	
	public void update(Item item){
		
	}
	
	@SuppressWarnings("unchecked")
	List<Item> list(){
        EntityManager em = null;
        
        try {
            em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
            Query query = em.createQuery("From Item");
            return (List<Item>)query.getResultList();
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
	}
	
	 ItemDetails get(String id){
        EntityManager em = null;    
        try {
            em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
            Query query = em.createQuery("From ItemDetails it where it.id = :id");
            query.setParameter("id", id);
			@SuppressWarnings("rawtypes")
			List resultList = query.getResultList();
            if (resultList.size() >= 1) 
				return (ItemDetails)resultList.get(0);
            return null;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
	}
	 

	void update(ItemDetails itd) {
        EntityManager em = null;    
        EntityTransaction tx = null;
        try {
            em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
            tx = em.getTransaction();        
            tx.begin();
            em.merge(itd);
            Item item = itd.toItem();
            em.merge(item);
            tx.commit();
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }			
	}

	void save(ItemDetails itd) {
        EntityManager em = null;  
        EntityTransaction tx = null;        
        try {            
            em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        	tx = em.getTransaction();        
        	tx.begin();        	
            em.persist(itd);
            Item item = itd.toItem();
            em.persist(item);
            tx.commit();
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }		
	}
}
