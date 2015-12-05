package edu.herguan.cs612.termproject.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.herguan.cs612.termproject.StaticRestService;

public class ResourceLoader {

	static public StringBuilder loadItemList() {
		StringBuilder responseBuilder = new StringBuilder();
		try {
			InputStream is = StaticRestService.class.getResourceAsStream("/phones/phones.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBuilder.append(line).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Parsing: - ");
		}
		return responseBuilder;
	}
 
	static public StringBuilder loadItemDetail(String item) {
		StringBuilder responseBuilder = new StringBuilder();
		try {
			InputStream is = StaticRestService.class.getResourceAsStream("/phones/"+item+".json");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBuilder.append(line).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Parsing: - "+item);
		}
		return responseBuilder;
	}
}
