package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Records.ConfigRecord;
import System.DriverManager;
import System.KeywordManager;

public class TC4_VerifySortOrderPayeeName 
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
		  KeywordManager.Execute(AutoDriver, 3, "Click add", "Payees", "Add", "Click", null);
		  KeywordManager.Execute(AutoDriver, 4, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{"AA MASTERCARD"});
		  KeywordManager.Execute(AutoDriver, 5, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{"123456789012"});
		  KeywordManager.Execute(AutoDriver, 6, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{"4321"});
		  KeywordManager.Execute(AutoDriver, 7, "Click add", "Payees_Add", "Add", "Click", null);
		  KeywordManager.Execute(AutoDriver, 8, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{"ascending"});
		  KeywordManager.Execute(AutoDriver, 9, "Click payee name sort", "Payees", "Sort_Name", "Click", null);
		  KeywordManager.Execute(AutoDriver, 10, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{"descending"});
	  }  
	  
	 @AfterClass
	 public void afterClass() throws Exception 
	 {
		 DriverManager.Quit(AutoDriver);
	 }   
}
