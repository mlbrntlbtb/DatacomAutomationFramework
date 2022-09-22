package System;

import java.util.HashMap;

import Utilities.ConfigHandler;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationManager 
{
	private static String baseURL;
	private static String contentFormat;
	private static String authorization;
	private static RequestSpecification requestSpecification;
	private static Response response;
	
	@SuppressWarnings("serial")
	private static final HashMap<String,ContentType> HTTPContentType = new HashMap<String,ContentType>()
	{{
		put("application/json", ContentType.JSON);
		put("application/xml", ContentType.XML);
		put("application/text", ContentType.TEXT);
	}};
	
	public static void CreateRequest() throws Exception 
	{
		try 
		{
			LogHandler.info("Creating request... ");
			baseURL = ConfigHandler.GetProperty("config","test.api.url");
			contentFormat = ConfigHandler.GetProperty("config","content.type");
			authorization = ConfigHandler.GetProperty("config","auth.key");
			
			LogHandler.info("Setting base URL: [" + baseURL + "]... ");
			RestAssured.baseURI = baseURL;
			requestSpecification = RestAssured.given();
			
			if(contentFormat != null) 
			{
				LogHandler.info("Setting content type: [" + contentFormat + "]... ");
				requestSpecification.contentType(HTTPContentType.get(contentFormat));
			}
			
			if(authorization != null) 
			{
				LogHandler.info("Setting authorization: [" + authorization + "]... ");
				requestSpecification.header("Authorization", authorization);
			}
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseCode() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static void SetResponse(String requestType, String param) throws Exception 
	{
		switch(requestType.toLowerCase()) 
		{
			case "get":
				response = param == null ? requestSpecification.get() : requestSpecification.get(param);
				break;
			case "post":
				response = param == null ? requestSpecification.post() : requestSpecification.post(param);
				break;
			case "put":
				response = param == null ? requestSpecification.put() : requestSpecification.put(param);
				break;
			case "delete":
				response = param == null ? requestSpecification.delete() : requestSpecification.delete(param);
				break;
			case "patch":
				response = param == null ? requestSpecification.patch() : requestSpecification.patch(param);
				break;
			case "options":
				response = param == null ? requestSpecification.options() : requestSpecification.options(param);
				break;
			default:
				throw new Exception("Request type not supported: [" + requestType + "]. Supported request types: [get|post|put|delete|options]");
		}
	}
	
	public static RequestSpecification GetRequest() 
	{
		return requestSpecification;
	}
	
	public static Response GetResponse() 
	{
		return response;
	}
}
