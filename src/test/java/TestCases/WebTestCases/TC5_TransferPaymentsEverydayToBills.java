package TestCases.WebTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import System.DriverManager;
import System.KeywordManager;
import Utilities.ConfigHandler;

public class TC5_TransferPaymentsEverydayToBills 
{
	WebDriver driver;		
	  
	 @Parameters({"browserName"})
	 @BeforeClass
	 public void beforeClass(String browserName) throws Exception 
	 {
		  driver = DriverManager.Initialize(browserName);
		  DriverManager.LoadApplication(driver, ConfigHandler.GetProperty("config","test.env"));
	 }
	  
	 @Parameters({"evrAcc", "blsAcc", "amount", "successTransMsg"})
	 @Test()
	  public void test(String evrAcc, String blsAcc, String amount, String successTransMsg) throws Exception 
	  {
		  KeywordManager.Execute(driver, 1, "Verify Account details is displayed", "Home", "AccountDetails", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 2, "Get current Everyday balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{evrAcc,"EverydayBalance"});
		  KeywordManager.Execute(driver, 3, "Get current Bills balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{blsAcc,"BillsBalance"});
		  KeywordManager.Execute(driver, 4, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(driver, 5, "Select Pay or transfer menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{"Pay or transfer"});
		  KeywordManager.Execute(driver, 6, "Verify Pay or transfer page", "PayTransfer", "Transfer", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 7, "Click From account", "PayTransfer", "FromAccount", "Click", null);
		  KeywordManager.Execute(driver, 8, "Verify From account dialog is displayed", "PayTransfer_From", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 9, "Select Everyday account", "PayTransfer_From", "List_From", "SelectPartialItemValue", new String[]{evrAcc});
		  KeywordManager.Execute(driver, 10, "Verify Everyday account has been selected", "PayTransfer", "FromAccount", "VerifyPartialValue", new String[]{evrAcc});
		  KeywordManager.Execute(driver, 11, "Click To account", "PayTransfer", "ToAccount", "Click", null);
		  KeywordManager.Execute(driver, 12, "Verify To account dialog is displayed", "PayTransfer_To", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(driver, 13, "Select Accounts tab", "PayTransfer_To", "TabList", "SelectTabItem", new String[]{"Accounts"});
		  KeywordManager.Execute(driver, 14, "Select Bills account", "PayTransfer_To", "List_To", "SelectPartialItemValue", new String[]{blsAcc});
		  KeywordManager.Execute(driver, 15, "Verify Bills account has been selected", "PayTransfer", "ToAccount", "VerifyPartialValue", new String[]{blsAcc});
		  KeywordManager.Execute(driver, 16, "Set 500 to Amounts field", "PayTransfer", "Amount", "SetValue", new String[]{amount});
		  KeywordManager.Execute(driver, 17, "Click Transfer", "PayTransfer", "Transfer", "Click", null);
		  KeywordManager.Execute(driver, 18, "Verify successful message display", "Alert", "Alert", "VerifyValue", new String[]{successTransMsg});
		  KeywordManager.Execute(driver, 19, "Verify current Everyday balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{evrAcc,"O{EverydayBalance}","subtract",amount});
		  KeywordManager.Execute(driver, 20, "Verify current Bills balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{blsAcc,"O{BillsBalance}","add",amount});
	  }  
	  
	 @AfterClass
	 public void afterClass() throws Exception 
	 {
		 DriverManager.Quit(driver);
	 }  
}
