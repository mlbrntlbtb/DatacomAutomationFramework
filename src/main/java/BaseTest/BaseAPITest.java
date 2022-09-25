package BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import System.RequestSpecificationManager;
import Utilities.DataProviderHandler;

public class BaseAPITest 
{
	@BeforeClass
	public void beforeClass() throws Exception 
	{
		RequestSpecificationManager.CreateRequest();
	} 	
}
