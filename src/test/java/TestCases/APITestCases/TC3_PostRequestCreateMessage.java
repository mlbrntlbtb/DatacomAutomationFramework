package TestCases.APITestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import System.RequestManager;
import System.RequestSpecificationManager;

public class TC3_PostRequestCreateMessage 
{
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
	} 	
	
	@Parameters({"statusCode", "statusLine"})
	@Test
	public void test(String statusCode, String statusLine) throws Exception 
	{
		RequestManager.Execute(1, "Send POST Request", "Request-Post", "SendRequest", new Object[] { null, null, null});
		RequestManager.Execute(2, "Verify GET Response Status Code", "Response", "VerifyResponseStatusCode", new Object[] {statusCode});
		RequestManager.Execute(3, "Verify GET Response Status Line", "Response", "VerifyResponseStatusLine", new Object[] {statusLine});
	}
}
