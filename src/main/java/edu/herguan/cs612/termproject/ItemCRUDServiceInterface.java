package edu.herguan.cs612.termproject;

import javax.ws.rs.core.Response;

public interface ItemCRUDServiceInterface {

	Response itemList();

	Response itemDetail(String item);

	Response verifyRESTService();

}