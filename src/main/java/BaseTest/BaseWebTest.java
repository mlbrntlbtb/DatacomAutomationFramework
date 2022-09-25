package BaseTest;

import org.testng.annotations.*;
import System.*;
import Utilities.*;

@Test(dataProvider="dataProvider", dataProviderClass=DataProviderHandler.class)
public class BaseWebTest 
{
	  protected String BrowserName;
	  protected String testName;
	  
	  @Parameters({"browserName","fileName","fileSheet"})
	  @BeforeClass
	  public void beforeClass(String browserName, String fileName, String fileSheet) throws Exception 
	  {
		  BrowserName = browserName;
		  testName = getClass().getName();
		  DataProviderHandler.SetFile(fileName, fileSheet);
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() throws Exception 
	  {
		  DriverManager.Initialize(BrowserName);
		  DriverManager.LoadApplication(ConfigHandler.GetProperty("config","test.env"));
	  }
	  
	  @AfterMethod
	  public void afterMethod() throws Exception 
	  {
		  DriverManager.Quit();
	  }
}
