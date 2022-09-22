package TestCases.APITestCases.CRUD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import System.RequestManager;
import System.RequestSpecificationManager;
import Utilities.ConfigHandler;
import Utilities.TestDataHandler;

public class TC2_GetRequest 
{
	Object[] body;
	Object[] header;
	String baseResource;
	
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
		TestDataHandler.SetFile("TestData.xlsx", "Body");
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
			RequestManager.Execute(1, "Send GET Request", "Request-Get", "SendRequest", new Object[] {header, null, baseResource});
			RequestManager.Execute(2, "Verify GET Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {"OK"});
			RequestManager.Execute(3, "Verify GET Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {"OK"});
			RequestManager.Execute(4, "Verify GET Response Header Content-Type", "Response", "VerifyResponseHeader", new Object[] {"Content-Type","application/json; charset=utf-8"});
			RequestManager.Execute(5, "Verify GET Response Body", "Response", "VerifyResponseBody", new Object[] {bodyInstance});
		}
	}	 
}
