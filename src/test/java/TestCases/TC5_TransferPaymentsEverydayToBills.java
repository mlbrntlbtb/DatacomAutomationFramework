package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Records.ConfigRecord;
import System.DriverManager;
import System.KeywordManager;

public class TC5_TransferPaymentsEverydayToBills 
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
		  KeywordManager.Execute(AutoDriver, 1, "Verify Account details is displayed", "Home", "AccountDetails", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 2, "Get current Everyday balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{"Everyday","EverydayBalance"});
		  KeywordManager.Execute(AutoDriver, 3, "Get current Bills balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{"Bills","BillsBalance"});
		  KeywordManager.Execute(AutoDriver, 4, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(AutoDriver, 5, "Select Pay or transfer menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{"Pay or transfer"});
		  KeywordManager.Execute(AutoDriver, 6, "Verify Pay or transfer page", "PayTransfer", "Transfer", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 7, "Click From account", "PayTransfer", "FromAccount", "Click", null);
		  KeywordManager.Execute(AutoDriver, 8, "Verify From account dialog is displayed", "PayTransfer_From", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 9, "Select Everyday account", "PayTransfer_From", "List_From", "SelectPartialItemValue", new String[]{"Everyday"});
		  KeywordManager.Execute(AutoDriver, 10, "Verify Everyday account has been selected", "PayTransfer", "FromAccount", "VerifyPartialValue", new String[]{"Everyday"});
		  KeywordManager.Execute(AutoDriver, 11, "Click To account", "PayTransfer", "ToAccount", "Click", null);
		  KeywordManager.Execute(AutoDriver, 12, "Verify To account dialog is displayed", "PayTransfer_To", "Title", "VerifyExist", new String[]{"True"});
		  KeywordManager.Execute(AutoDriver, 13, "Select Accounts tab", "PayTransfer_To", "TabList", "SelectTabItem", new String[]{"Accounts"});
		  KeywordManager.Execute(AutoDriver, 14, "Select Bills account", "PayTransfer_To", "List_To", "SelectPartialItemValue", new String[]{"Bills"});
		  KeywordManager.Execute(AutoDriver, 15, "Verify Bills account has been selected", "PayTransfer", "ToAccount", "VerifyPartialValue", new String[]{"Bills"});
		  KeywordManager.Execute(AutoDriver, 16, "Set 500 to Amounts field", "PayTransfer", "Amount", "SetValue", new String[]{"500"});
		  KeywordManager.Execute(AutoDriver, 17, "Click Transfer", "PayTransfer", "Transfer", "Click", null);
		  KeywordManager.Execute(AutoDriver, 18, "Verify successful message display", "Alert", "Alert", "VerifyValue", new String[]{"Transfer successful"});
		  KeywordManager.Execute(AutoDriver, 19, "Verify current Everyday balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{"Everyday","O{EverydayBalance}","subtract","500"});
		  KeywordManager.Execute(AutoDriver, 20, "Verify current Bills balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{"Bills","O{BillsBalance}","add","500"});
	  }  
	  
	 @AfterClass
	 public void afterClass() throws Exception 
	 {
		 DriverManager.Quit(AutoDriver);
	 }  
}
