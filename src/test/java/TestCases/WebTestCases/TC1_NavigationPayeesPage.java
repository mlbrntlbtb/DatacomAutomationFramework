package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import System.*;
import Utilities.*;

public class TC1_NavigationPayeesPage 
{
  WebDriver driver;
  
  @Parameters({"browserName"})
  @BeforeClass
  public void beforeClass(String browserName) throws Exception 
  {
	  driver = DriverManager.Initialize(browserName);
	  DriverManager.LoadApplication(driver, ConfigHandler.GetProperty("config","test.env"));
  }
	
  @Parameters({"menu"})
  @Test
  public void test(String menu) throws Exception 
  {
	  KeywordManager.Execute(driver, 1, "Click main menu", "Home", "Menu", "Click", null);
	  KeywordManager.Execute(driver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
	  KeywordManager.Execute(driver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
  }
  
  @AfterClass
  public void afterClass() throws Exception 
  {
	  DriverManager.Quit(driver);
  }
}
