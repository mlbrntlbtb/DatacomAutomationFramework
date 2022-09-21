package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import Records.ConfigRecord;
import System.*;

public class TC1_NavigationPayeesPage 
{
  WebDriver AutoDriver;
  
  @Parameters({"browserName"})
  @BeforeClass
  public void beforeClass(String browserName) throws Exception 
  {
	  AutoDriver = DriverManager.Initialize(browserName);
	  DriverManager.LoadApplication(AutoDriver, ConfigRecord.GetProperty("test.env"));
  }
	
  @Test
  public void test() throws Exception 
  {
	  KeywordManager.Execute(AutoDriver, 1, "Click main menu", "Home", "Menu", "Click", null);
	  KeywordManager.Execute(AutoDriver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{"Payees"});
	  KeywordManager.Execute(AutoDriver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
  }
  
  @AfterClass
  public void afterClass() throws Exception 
  {
	  DriverManager.Quit(AutoDriver);
  }
}
