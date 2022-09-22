package TestCases.APITestCases.CRUD;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import System.*;
import Utilities.*;


public class TC1_PostRequest 
{
	Object[] body;
	Object[] header;
	
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
		TestDataHandler.SetFile("TestData.xlsx", "Body");
		body = TestDataHandler.GetData();
		TestDataHandler.SetFile("TestData.xlsx", "Header");
		header = TestDataHandler.GetData();
	} 	
	
	@Test
	public void test() throws Exception 
	{
		for(Object bodyInstance : body) 
		{
			RequestManager.Execute(1, "Send POST Request", "Request-Post", "SendRequest", new Object[] { header, bodyInstance, null});
			RequestManager.Execute(2, "Verify POST Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {"CREATED"});
			RequestManager.Execute(3, "Verify POST Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {"CREATED"});
			RequestManager.Execute(4, "Verify POST Response Header Content-Type", "Response", "VerifyResponseHeader", new Object[] {"Content-Type","application/json; charset=utf-8"});
			RequestManager.Execute(5, "Verify POST Response Body", "Response", "VerifyResponseBody", new Object[] {bodyInstance});
			RequestManager.Execute(6, "Get POST Response Body ID", "Response", "GetResponseBodyItem", new Object[] {"id","id"});
		}
	}	 
}
