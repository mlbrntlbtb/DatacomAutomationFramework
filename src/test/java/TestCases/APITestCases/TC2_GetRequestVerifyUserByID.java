package TestCases.APITestCases;

import org.testng.annotations.*;

import BaseTest.BaseAPITest;
import System.RequestManager;

public class TC2_GetRequestVerifyUserByID extends BaseAPITest
{	
	@Parameters({"queryParam", "statusCode", "statusLine", "baseResource", "baseValue"})
	@Test
	public void test(String queryParam, String statusCode, String statusLine, String baseResource, String baseValue) throws Exception 
	{
		RequestManager.Execute(1, "Send GET Request", "Request-Get", "SendRequest", new Object[] { null, null, "?id=8"});
		RequestManager.Execute(2, "Verify GET Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {statusCode});
		RequestManager.Execute(3, "Verify GET Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {statusLine});
		RequestManager.Execute(4, "Verify GET Response Body Item by Name", "Response", "VerifyResponseBodyByValue", new Object[] {baseResource,baseValue});
	}	
}
