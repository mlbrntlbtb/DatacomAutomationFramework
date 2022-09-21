package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import Records.ConfigRecord;
import System.DriverManager;
import System.KeywordManager;

public class TC2_AddingNewPayee 
{
	  WebDriver AutoDriver;		
	  
	  @Parameters({"browserName"})
	  @BeforeClass
	  public void beforeClass(String browserName) throws Exception 
	  {
		  AutoDriver = DriverManager.Initialize(browserName);
		  DriverManager.LoadApplication(AutoDriver, ConfigRecord.GetProperty("test.env"));
	  }
	  
	  @Test()
	  public void test() throws Exception 
	  {
		  KeywordManager.Execute(AutoDriver, 1, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(AutoDriver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{"Payees"});
		  KeywordManager.Execute(AutoDriver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 4, "Click add", "Payees", "Add", "Click", null);
		  KeywordManager.Execute(AutoDriver, 5, "Verify Add Payees page has loaded", "Payees_Add", "PayeeName", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 6, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{"AA MASTERCARD"});
		  KeywordManager.Execute(AutoDriver, 7, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{"123456789012"});
		  KeywordManager.Execute(AutoDriver, 8, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{"4321"});
		  KeywordManager.Execute(AutoDriver, 9, "Click add", "Payees_Add", "Add", "Click", null);
		  KeywordManager.Execute(AutoDriver, 10, "Verify message 'Payee added' is displayed", "Alert", "Alert", "VerifyValue", new String[]{"Payee added"});
	  }
	  
	  @AfterClass
	  public void afterClass() throws Exception 
	  {
		  DriverManager.Quit(AutoDriver);
	  }
}
