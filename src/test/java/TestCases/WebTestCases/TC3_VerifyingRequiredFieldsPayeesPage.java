package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import System.DriverManager;
import System.KeywordManager;
import Utilities.*;

public class TC3_VerifyingRequiredFieldsPayeesPage 
{
	WebDriver driver;		
	  
	 @Parameters({"browserName"})
	 @BeforeClass
	 public void beforeClass(String browserName) throws Exception 
	 {
		 driver = DriverManager.Initialize(browserName);
		 DriverManager.LoadApplication(driver, ConfigHandler.GetProperty("config","test.env"));
	 }
	  
     @Parameters({"menu", "payeeName", "creditCard1st", "creditCard2nd", "errorHeader", "toolTip"})
	 @Test()
	  public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String errorHeader, String toolTip) throws Exception 
	  {
		  KeywordManager.Execute(driver, 1, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(driver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		  KeywordManager.Execute(driver, 3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 4, "Click add", "Payees", "Add", "Click", null);
		  KeywordManager.Execute(driver, 5, "Click add again new dialog", "Payees_Add", "Add", "Click", null);
		  KeywordManager.Execute(driver, 6, "Verify error header exist", "Payees_Add", "ErrorHeader", "VerifyExist", new String[] {"True"});
		  KeywordManager.Execute(driver, 7, "Verify error header content", "Payees_Add", "ErrorHeader", "VerifyValue", new String[] {errorHeader});
		  KeywordManager.Execute(driver, 8, "Verify error tooltip exist", "Payees_Add", "ToolTip", "VerifyExist", new String[] {"True"});
		  KeywordManager.Execute(driver, 9, "Verify error tooltip content", "Payees_Add", "ToolTip", "VerifyValue", new String[] {toolTip});
		  KeywordManager.Execute(driver, 10, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		  KeywordManager.Execute(driver, 11, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		  KeywordManager.Execute(driver, 12, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		  KeywordManager.Execute(driver, 13, "Verify error tooltip", "Payees_Add", "ToolTip", "VerifyExistWithText", new String[] {"False",toolTip,"5"});
	  }  
	  
	 @AfterClass
	 public void afterClass() throws Exception 
	 {
		 DriverManager.Quit(driver);
	 }   
}
