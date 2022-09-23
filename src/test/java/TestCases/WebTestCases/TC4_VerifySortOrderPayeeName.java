package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import System.DriverManager;
import System.KeywordManager;
import Utilities.ConfigHandler;
import Utilities.DataProviderHandler;

public class TC4_VerifySortOrderPayeeName 
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
	public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String sortAsc, String sortDesc) throws Exception 
	{
		KeywordManager.Execute(driver, 1, "Click main menu", "Home", "Menu", "Click", null);
		KeywordManager.Execute(driver, 2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		KeywordManager.Execute(driver, 3, "Click add", "Payees", "Add", "Click", null);
		KeywordManager.Execute(driver, 4, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		KeywordManager.Execute(driver, 5, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		KeywordManager.Execute(driver, 6, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		KeywordManager.Execute(driver, 7, "Click add", "Payees_Add", "Add", "Click", null);
		KeywordManager.Execute(driver, 8, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{sortAsc});
		KeywordManager.Execute(driver, 9, "Click payee name sort", "Payees", "Sort_Name", "Click", null);
		KeywordManager.Execute(driver, 10, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{sortDesc});
	}  
	  
	 @AfterMethod
	 public void afterMethod() throws Exception 
	 {
		 DriverManager.Quit(driver);
	 }   
}
