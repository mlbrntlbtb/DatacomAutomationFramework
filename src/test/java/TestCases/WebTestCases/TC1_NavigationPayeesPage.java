package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import System.*;
import Utilities.*;

public class TC1_NavigationPayeesPage 
{
  WebDriver driver;
  String BrowserName;
  
  @Parameters({"browserName","fileName","fileSheet"})
  @BeforeClass
  public void beforeClass(String browserName, String fileName, String fileSheet) throws Exception 
  {
	  BrowserName = browserName;
	  DataProviderHandler.SetFile(fileName, fileSheet);
  }
  
  @BeforeMethod
  public void beforeMethod() throws Exception 
  {
	  driver = DriverManager.Initialize(BrowserName);
	  DriverManager.LoadApplication(driver, ConfigHandler.GetProperty("config","test.env"));
  }
	
  @Test(dataProvider="dataProvider", dataProviderClass=DataProviderHandler.class)
  public void test(String menu) throws Exception 
  {
	  KeywordManager.Execute(driver, 1, "Click main menu", "Home", "Menu", "Click", null);
	  KeywordManager.Execute(driver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
	  KeywordManager.Execute(driver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
  }
  
  @AfterMethod
  public void afterMethod() throws Exception 
  {
	  DriverManager.Quit(driver);
  }
}
