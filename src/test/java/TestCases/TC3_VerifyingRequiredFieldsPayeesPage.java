package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Records.ConfigRecord;
import System.DriverManager;
import System.KeywordManager;

public class TC3_VerifyingRequiredFieldsPayeesPage 
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
		  KeywordManager.Execute(AutoDriver, 5, "Click add again new dialog", "Payees_Add", "Add", "Click", null);
		  KeywordManager.Execute(AutoDriver, 6, "Verify error header exist", "Payees_Add", "ErrorHeader", "VerifyExist", new String[] {"True"});
		  KeywordManager.Execute(AutoDriver, 7, "Verify error header content", "Payees_Add", "ErrorHeader", "VerifyValue", new String[] {"A problem was found. Please correct the field highlighted below."});
		  KeywordManager.Execute(AutoDriver, 8, "Verify error tooltip exist", "Payees_Add", "ToolTip", "VerifyExist", new String[] {"True"});
		  KeywordManager.Execute(AutoDriver, 9, "Verify error tooltip content", "Payees_Add", "ToolTip", "VerifyValue", new String[] {"Payee Name is a required field. Please complete to continue."});
		  KeywordManager.Execute(AutoDriver, 10, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{"AA MASTERCARD"});
		  KeywordManager.Execute(AutoDriver, 11, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{"123456789012"});
		  KeywordManager.Execute(AutoDriver, 12, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{"4321"});
		  KeywordManager.Execute(AutoDriver, 13, "Verify error tooltip", "Payees_Add", "ToolTip", "VerifyExistWithText", new String[] {"False","Payee Name is a required field. Please complete to continue.","5"});
	  }  
	  
	 @AfterClass
	 public void afterClass() throws Exception 
	 {
		 DriverManager.Quit(AutoDriver);
	 }   
}
