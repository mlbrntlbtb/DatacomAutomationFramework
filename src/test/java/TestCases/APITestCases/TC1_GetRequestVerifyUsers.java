package TestCases.APITestCases;

import org.testng.annotations.*;

import BaseTest.BaseAPITest;
import System.*;


public class TC1_GetRequestVerifyUsers extends BaseAPITest
{	
	@Parameters({"statusCode", "statusLine", "baseResource"})
	@Test
	public void test(String statusCode, String statusLine, String baseResource) throws Exception 
	{
		RequestManager.Execute(1, "Send GET Request", "Request-Get", "SendRequest", new Object[] { null, null, null});
		RequestManager.Execute(2, "Verify GET Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {statusCode});
		RequestManager.Execute(3, "Verify GET Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {statusLine});
		RequestManager.Execute(4, "Get GET Response Body Count by ID", "Response", "GetResponseBodyCountByValue", new Object[] {baseResource,"id_count"});
		RequestManager.Execute(5, "Verify GET Response Body Count by ID", "Response", "VerifyResponseBodyCountByValue", new Object[] {baseResource,"O{id_count}"});
	}	 
}
