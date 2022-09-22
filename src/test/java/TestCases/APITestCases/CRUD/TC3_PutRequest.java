package TestCases.APITestCases.CRUD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import System.RequestManager;
import System.RequestSpecificationManager;import Utilities.ConfigHandler;
import Utilities.TestDataHandler;

public class TC3_PutRequest 
{
	Object[] body;
	Object[] header;
	String baseResource;
	
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
		TestDataHandler.SetFile("TestData.xlsx", "UpdateBody");
		body = TestDataHandler.GetData();
		TestDataHandler.SetFile("TestData.xlsx", "Header");
		header = TestDataHandler.GetData();
		baseResource = "/" + ConfigHandler.GetProperty("global","id");
	} 	
	
	@Test
	public void test() throws Exception 
	{
		for(Object bodyInstance : body) 
		{
			RequestManager.Execute(1, "Send PUT Request", "Request-Put", "SendRequest", new Object[] { header, bodyInstance, baseResource});
			RequestManager.Execute(2, "Verify PUT Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {"OK"});
			RequestManager.Execute(3, "Verify PUT Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {"OK"});
			RequestManager.Execute(4, "Verify PUT Response Header Content-Type", "Response", "VerifyResponseHeader", new Object[] {"Content-Type","application/json; charset=utf-8"});
			RequestManager.Execute(5, "Verify PUT Response Body", "Response", "VerifyResponseBody", new Object[] {bodyInstance});
			RequestManager.Execute(6, "Get PUT Response Body ID", "Response", "GetResponseBodyItem", new Object[] {"id","idVariable"});
		}
	}	 
}
