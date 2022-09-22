package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import System.DriverManager;
import System.KeywordManager;
import Utilities.*;

public class TC2_AddingNewPayee 
{
	  WebDriver driver;		
	  
	  @Parameters({"browserName"})
	  @BeforeClass
	  public void beforeClass(String browserName) throws Exception 
	  {
		  driver = DriverManager.Initialize(browserName);
		  DriverManager.LoadApplication(driver, ConfigHandler.GetProperty("config","test.env"));
	  }
	  
	  @Parameters({"menu", "payeeName", "creditCard1st", "creditCard2nd", "successAddMsg"})
	  @Test()
	  public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String successAddMsg) throws Exception 
	  {
		  KeywordManager.Execute(driver, 1, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(driver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		  KeywordManager.Execute(driver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 4, "Click add", "Payees", "Add", "Click", null);
		  KeywordManager.Execute(driver, 5, "Verify Add Payees page has loaded", "Payees_Add", "PayeeName", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 6, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		  KeywordManager.Execute(driver, 7, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		  KeywordManager.Execute(driver, 8, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		  KeywordManager.Execute(driver, 9, "Click add", "Payees_Add", "Add", "Click", null);
		  KeywordManager.Execute(driver, 10, "Verify message 'Payee added' is displayed", "Alert", "Alert", "VerifyValue", new String[]{successAddMsg});
	  }
	  
	  @AfterClass
	  public void afterClass() throws Exception 
	  {
		  DriverManager.Quit(driver);
	  }
}
