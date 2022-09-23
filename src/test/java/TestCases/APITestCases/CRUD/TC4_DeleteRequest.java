package TestCases.APITestCases.CRUD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import System.RequestManager;
import System.RequestSpecificationManager;
import Utilities.ConfigHandler;
import Utilities.TestDataHandler;

public class TC4_DeleteRequest 
{
	Object[] body;
	Object[] header;
	String baseResource;
	
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
		TestDataHandler.SetFile("API_Request_TestData.xlsx", "Header");
		header = TestDataHandler.GetData();
		baseResource = "/" + ConfigHandler.GetProperty("global","id");
	} 	
	
	@Test
	public void test() throws Exception 
	{
		RequestManager.Execute(1, "Send DELETE Request", "Request-Delete", "SendRequest", new Object[] {header, null, baseResource});
		RequestManager.Execute(2, "Verify DELETE Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {"DELETED"});
		RequestManager.Execute(3, "Verify DELETE Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {"DELETED"});
	}	
}
