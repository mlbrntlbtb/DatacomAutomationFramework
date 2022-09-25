package TestCases.WebTestCases;

import org.testng.annotations.*;
import BaseTest.BaseWebTest;
import System.KeywordManager;
import Utilities.DataProviderHandler;

public class TC5_TransferPaymentsEverydayToBills extends BaseWebTest
{
	@Test
	public void test(String evrAcc, String blsAcc, String amount, String successTransMsg) throws Exception 
	{
		KeywordManager.Execute(1, "Verify Account details is displayed", "Home", "AccountDetails", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(2, "Get current Everyday balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{evrAcc,"EverydayBalance"});
		KeywordManager.Execute(3, "Get current Bills balance", "Home", "AccountDetails", "GetThumbnailBalance", new String[]{blsAcc,"BillsBalance"});
		KeywordManager.Execute(4, "Click main menu", "Home", "Menu", "Click", null);
		KeywordManager.Execute(5, "Select Pay or transfer menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{"Pay or transfer"});
		KeywordManager.Execute(6, "Verify Pay or transfer page", "PayTransfer", "Transfer", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(7, "Click From account", "PayTransfer", "FromAccount", "Click", null);
		KeywordManager.Execute(8, "Verify From account dialog is displayed", "PayTransfer_From", "Title", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(9, "Select Everyday account", "PayTransfer_From", "List_From", "SelectPartialItemValue", new String[]{evrAcc});
		KeywordManager.Execute(10, "Verify Everyday account has been selected", "PayTransfer", "FromAccount", "VerifyPartialValue", new String[]{evrAcc});
		KeywordManager.Execute(11, "Click To account", "PayTransfer", "ToAccount", "Click", null);
		KeywordManager.Execute(12, "Verify To account dialog is displayed", "PayTransfer_To", "Title", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(13, "Select Accounts tab", "PayTransfer_To", "TabList", "SelectTabItem", new String[]{"Accounts"});
		KeywordManager.Execute(14, "Select Bills account", "PayTransfer_To", "List_To", "SelectPartialItemValue", new String[]{blsAcc});
		KeywordManager.Execute(15, "Verify Bills account has been selected", "PayTransfer", "ToAccount", "VerifyPartialValue", new String[]{blsAcc});
		KeywordManager.Execute(16, "Set 500 to Amounts field", "PayTransfer", "Amount", "SetValue", new String[]{amount});
		KeywordManager.Execute(17, "Click Transfer", "PayTransfer", "Transfer", "Click", null);
		KeywordManager.Execute(18, "Verify successful message display", "Alert", "Alert", "VerifyValue", new String[]{successTransMsg});
		KeywordManager.Execute(19, "Verify current Everyday balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{evrAcc,"O{EverydayBalance}","subtract",amount});
		KeywordManager.Execute(20, "Verify current Bills balance", "Home", "AccountDetails", "VerifyThumbnailBalance", new String[]{blsAcc,"O{BillsBalance}","add",amount});
	} 
}
