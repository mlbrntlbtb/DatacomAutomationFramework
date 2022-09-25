package BaseHTTP;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import System.*;

public class BaseRequest 
{
	//Private variables
	private RequestSpecification RequestSpec;
	private String RequestType;
	
	public BaseRequest(RequestSpecification requestSpec, String requestType) 
	{
		this.RequestSpec = requestSpec;
		this.RequestType = requestType;
	}
		
	@SuppressWarnings("unchecked")
	public void SendRequest(Object[] objectArrayHeader, Object objectBody, String paramValue) throws Exception 
	{
		if(objectArrayHeader != null) 
		{
			for(Object objectHeader : objectArrayHeader) 
			{
				Iterator<Entry<String,String>> headers = ((LinkedHashMap<String,String>)objectHeader).entrySet().iterator();
				while(headers.hasNext()) 
				{
					Entry<String,String> header = headers.next();
					String headerKey = header.getKey();
					String headerValue = header.getValue();
					LogHandler.info("Setting request header: [" + headerKey + "] as: [" + headerValue + "]");
					RequestSpec.header(headerKey,headerValue);
				}
			}
		}
		
		if(objectBody != null) 
		{
			LogHandler.info("Setting request body... ");
			LinkedHashMap<String,String> body = (LinkedHashMap<String,String>)objectBody;
			RequestSpec.body(body);
		}
		
		if(paramValue != null) 
			LogHandler.info("Setting request param value: [" + paramValue + "]");
		LogHandler.info("Setting request type: [" + RequestType + "]... ");
		RequestSpecificationManager.SetResponse(RequestType.toLowerCase(), paramValue);
		
		LogHandler.info("HTTP Request: [" + RequestType + "] sent.");
		String responseHeaders = null;
		for(Header header : RequestSpecificationManager.GetResponse().getHeaders()) 
		{
			responseHeaders = responseHeaders != null ? responseHeaders + "\n'" + header.getName() + "' : '" + header.getValue() + "'":
				"\n'" + header.getName() + "' : '" + header.getValue() + "'";
		}
		LogHandler.info("HTTP Response Header: [" + responseHeaders + "]");
		LogHandler.info("HTTP Response Body: [" + RequestSpecificationManager.GetResponse().body().asString() + "]");
	}
}
