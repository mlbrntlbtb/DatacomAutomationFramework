package BaseHTTP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import org.testng.Assert;
import System.RequestSpecificationManager;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import Utilities.VariableHandler;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseResponse 
{
	//Private variables
	private Response ResponseSpec;
	
	@SuppressWarnings("serial")
	private static final HashMap<String,String> HTTPStatusCode = new HashMap<String,String>()
	{{
		put("OK", "200");
		put("CREATED", "201");
		put("DELETED", "204");
	}};
	
	@SuppressWarnings("serial")
	private static final HashMap<String,String> HTTPStatusLine = new HashMap<String,String>()
	{{
		put("OK", "HTTP/1.1 200 OK");
		put("CREATED", "HTTP/1.1 201 Created");
		put("DELETED", "HTTP/1.1 204 No Content");
	}};		
	
	//Constructor
	public BaseResponse() 
	{
		this.ResponseSpec = RequestSpecificationManager.GetResponse();
	}
	
	//Private methods
	
	private String GetResponseStatusCode() 
	{
		return String.valueOf(ResponseSpec.getStatusCode());
	}
	
	private String GetResponseStatusLine()
	{
		return String.valueOf(ResponseSpec.getStatusLine());
	}
	
	private String GetResponseHeader(String headerKey) 
	{
		return String.valueOf(ResponseSpec.getHeader(headerKey));
	}
	
	private Iterator<Entry<String,String>> GetResponseHeaders() 
	{
		LinkedHashMap<String,String> headers = new LinkedHashMap<String,String>();
		
		for(Header header : ResponseSpec.getHeaders()) 
		{
			headers.put(header.getName(), header.getValue());
		}
		return headers.entrySet().iterator();
	}
	
	private Iterator<Entry<String,String>> GetResponseBody(Set<String> bodyHeaders) throws Exception 
	{
		if(ResponseSpec.contentType().contains("application/json")) 
		{
			JsonPath responseJson = ResponseSpec.jsonPath();
			LinkedHashMap<String,String> responseBody = new LinkedHashMap<String,String>();
			
			for(String bodyHeader : bodyHeaders) 
			{
				responseBody.put(bodyHeader, responseJson.getString(bodyHeader).replaceAll("\\[|\\]", ""));
			}
			return responseBody.entrySet().iterator();
		}
		else
			throw new Exception("Content type: [" + ResponseSpec.contentType() + "] not yet supported.");
	}	
	
	private List<String> GetResponseBodyListByValue(String bodyHeader) throws Exception 
	{
		if(ResponseSpec.contentType().contains("application/json")) 
		{
			JsonPath responseJson = ResponseSpec.jsonPath();
			List<String> responseItems = responseJson.getList(bodyHeader);
			return responseItems;
		}
		else
			throw new Exception("Content type: [" + ResponseSpec.contentType() + "] not yet supported.");
	}	
	
	private String GetResponseBodyByValue(String bodyHeader) throws Exception 
	{
		if(ResponseSpec.contentType().contains("application/json")) 
		{
			JsonPath responseJson = ResponseSpec.jsonPath();
			String responseItem = responseJson.getString(bodyHeader).replaceAll("\\[|\\]", "");
			return responseItem;
		}
		else
			throw new Exception("Content type: [" + ResponseSpec.contentType() + "] not yet supported.");
	}	
	
	//Keywords
	public void VerifyResponseStatusCode(Object objectResponseCode) throws Exception 
	{
		try 
		{
			String expectedResponseCode = (String)objectResponseCode;
			LogHandler.info("Verifying response status code: [" + expectedResponseCode + "]");
			String httpStatusCode = HTTPStatusCode.containsValue(expectedResponseCode) ? expectedResponseCode :
				HTTPStatusCode.get(expectedResponseCode);
			LogHandler.info("Expected status code: [" + httpStatusCode + "] Actual status code: [" + GetResponseStatusCode() + "]");
			Assert.assertEquals(GetResponseStatusCode(), httpStatusCode);
			LogHandler.info("VerifyResponseCode() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseCode() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void VerifyResponseStatusLine(Object objectResponseStatusLine) throws Exception
	{
		try 
		{
			String expectedResponseStatusLine = (String)objectResponseStatusLine;
			LogHandler.info("Verifying response status line: [" + expectedResponseStatusLine + "]");
			String httpStatusLine = HTTPStatusLine.containsValue(expectedResponseStatusLine) ? expectedResponseStatusLine :
				HTTPStatusLine.get(expectedResponseStatusLine);
			LogHandler.info("Expected status line: [" + httpStatusLine + "] Actual status line: [" + GetResponseStatusLine() + "]");
			Assert.assertEquals(GetResponseStatusLine(), httpStatusLine);
			LogHandler.info("VerifyResponseStatusLine() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseStatusLine() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void VerifyResponseHeader(Object objectResponseHeaderKey, Object objectResponseHeader) throws Exception 
	{
		try 
		{
			LogHandler.info("Verifying response status header... ");
			String expectResponseHeaderKey = (String)objectResponseHeaderKey;
			String expectResponseHeader = (String)objectResponseHeader;
			LogHandler.info("Expected response header: [" + expectResponseHeaderKey + "] [" + expectResponseHeader + "]");
			LogHandler.info("Actual response header: [" + GetResponseHeader(expectResponseHeaderKey) + "]");
			Assert.assertEquals(GetResponseHeader(expectResponseHeaderKey), expectResponseHeader);
			LogHandler.info("VerifyResponseHeader() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseHeader() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void VerifyResponseHeaders(Object objectHeaders) throws Exception 
	{
		try 
		{
			LogHandler.info("Verifying response headers... ");
			LinkedHashMap<String,String> verifyHeaders = (LinkedHashMap<String,String>)objectHeaders;
			Iterator<Entry<String,String>> expectedHeaders = verifyHeaders.entrySet().iterator();
			Iterator<Entry<String,String>> responseHeaders = GetResponseHeaders();
			
			while(expectedHeaders.hasNext() && responseHeaders.hasNext()) 
			{
				Entry<String,String> expectedHeader = expectedHeaders.next();
				Entry<String,String> responseHeader = responseHeaders.next();
				
				String expectHeader = expectedHeader.getKey();
				String expectHeaderValue = expectedHeader.getValue();
				String actualHeader = responseHeader.getKey();
				String actualHeaderValue = responseHeader.getValue();
				
				LogHandler.info("Expected response header: [" + expectHeader + "] [" + expectHeaderValue + "]");
				LogHandler.info("Actual response header: [" + actualHeader + "] [" + actualHeaderValue + "]");
				
				Assert.assertEquals(actualHeader, expectHeader);
				Assert.assertEquals(actualHeaderValue, expectHeaderValue);
			}
			
			LogHandler.info("VerifyResponseHeaders() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseHeaders() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void VerifyResponseBody(Object objectResponseBody) throws Exception 
	{
		try 
		{
			LogHandler.info("Verifying response body... ");
			LinkedHashMap<String,String> verifyResponseBody = (LinkedHashMap<String,String>)objectResponseBody;
			Iterator<Entry<String,String>> expectedResponseBody = verifyResponseBody.entrySet().iterator();
			Iterator<Entry<String,String>> responseBody= GetResponseBody(verifyResponseBody.keySet());
			
			while(responseBody.hasNext() && expectedResponseBody.hasNext()) 
			{
				Entry<String,String> expectResponseBody = expectedResponseBody.next();
				Entry<String,String> actualResponseBody = responseBody.next();
				
				String expectBodyHeader = expectResponseBody.getKey();
				String expectBodyValue = expectResponseBody.getValue();
				String actualBodyHeader = actualResponseBody.getKey();
				String actualBodyValue = actualResponseBody.getValue();
				
				LogHandler.info("Expected response body: [" + expectBodyHeader + ": " + expectBodyValue + "]");
				LogHandler.info("Actual response body: [" + actualBodyHeader + ": " + actualBodyValue + "]");
				
				Assert.assertEquals(actualBodyHeader, expectBodyHeader);
				Assert.assertEquals(actualBodyValue, expectBodyValue);
			}
			
			LogHandler.info("VerifyResponseBody() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseBody() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void VerifyResponseBodyByValue(Object bodyHeader, Object expectedValue) throws Exception 
	{
		try 
		{
			String bodyHead = String.valueOf(bodyHeader);
			LogHandler.info("Verifying response body by value: [" + bodyHead + "]... ");
			String responseBodyItem = GetResponseBodyByValue(bodyHead);
			String expectValue = String.valueOf(expectedValue);
			LogHandler.info("Expected response body value: [" + expectValue + "]");
			LogHandler.info("Actual response body count: [" + responseBodyItem + "]");
			Assert.assertEquals(responseBodyItem, expectValue);
			LogHandler.info("VerifyResponseBodyByValue() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseBodyByValue() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void GetResponseBodyCountByValue(Object bodyHeader, Object variableName) throws Exception 
	{
		try 
		{
			String bodyHead = String.valueOf(bodyHeader);
			String varName = String.valueOf(variableName);
			LogHandler.info("Getting response body count by value: [" + bodyHead + "]... ");
			List <String> responseBodyCount = GetResponseBodyListByValue(bodyHead);
			int actualCount = responseBodyCount.size();
			LogHandler.info("Response body count: [" + actualCount + "] assigned to Variable name: [" + varName + "]");
			VariableHandler.SetVariable(varName, String.valueOf(actualCount));
			LogHandler.info("GetResponseBodyCountByValue() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("GetResponseBodyCountByValue() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void VerifyResponseBodyCountByValue(Object bodyHeader, Object expectedCount) throws Exception 
	{
		try 
		{
			String bodyHead = String.valueOf(bodyHeader);
			LogHandler.info("Verifying response body count by value: [" + bodyHead + "]... ");
			List <String> responseBodyCount = GetResponseBodyListByValue(bodyHead);
			int expectCount = Integer.parseInt(String.valueOf(expectedCount));
			int actualCount = responseBodyCount.size();
			LogHandler.info("Expected response body count: [" + expectCount + "]"); 
			LogHandler.info("Actual response body count: [" + actualCount + "]");
			Assert.assertEquals(actualCount, expectCount);
			LogHandler.info("VerifyResponseBodyCountByValue() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("VerifyResponseBodyCountByValue() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void GetResponseBodyItem(String bodyHeader, String variableName) throws Exception 
	{
		try 
		{
			LogHandler.info("Getting response body item: [" + bodyHeader + "]");
			Set<String> bodyHeaders = new HashSet<String>();
			bodyHeaders.add(bodyHeader);
			Iterator<Entry<String,String>> responseBody= GetResponseBody(bodyHeaders);
			
			while(responseBody.hasNext()) 
			{
				Entry<String,String> actualResponseBody = responseBody.next();
				String actualBodyHeader = actualResponseBody.getKey();
				String actualBodyValue = actualResponseBody.getValue();
				
				if(actualBodyHeader.equalsIgnoreCase(bodyHeader)) 
				{
					LogHandler.info("Assigning reponse body value: [" + actualBodyValue + "] to Variable name: [" + variableName + "]");
					VariableHandler.SetVariable(variableName, actualBodyValue);
				}
			}
			
			LogHandler.info("GetResponseBodyItem() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("GetResponseBodyItem() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
}
