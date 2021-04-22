package com;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Funder;

@Path("/Funder")
public class FunderService {
	
	Funder funderObj = new Funder();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return funderObj. readFunddata();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("funderName") String funderName,
			@FormParam("category") String category,
			@FormParam("description") String description,
			@FormParam("fundingAmount") String fundingAmount,
			@FormParam("fundStartDate") String fundingstartdate,
			@FormParam("fundEndDate") String fundinendate)
	{
		String output = funderObj.insertFund(funderName, category, description, fundingAmount,fundingstartdate,fundinendate);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String id) {
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(id).getAsJsonObject();
		
		//Read the values from the JSON object
		String funderID = itemObject.get("funderID").getAsString();
		String funderName = itemObject.get("funderName").getAsString();
		String category = itemObject.get("category").getAsString();
		String description = itemObject.get("description").getAsString();
		String fundingAmount = itemObject.get("fundingAmount").getAsString();
		String fundingstartdate = itemObject.get("fundStartDate").getAsString();
		String fundinendate = itemObject.get("fundEndDate").getAsString();
		
		String output = funderObj.Updatefund(funderID, funderName, category, description, fundingAmount,fundingstartdate,fundinendate);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String id) {
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(id, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String funderID = doc.select("funderID").text();
		
		String output = funderObj.deletefundData(funderID);
		
		return output;
	}
	
	
}
