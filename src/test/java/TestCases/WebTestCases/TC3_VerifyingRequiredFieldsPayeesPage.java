package TestCases.WebTestCases;

import org.testng.annotations.*;
import BaseTest.BaseWebTest;
import System.KeywordManager;
import Utilities.*;

public class TC3_VerifyingRequiredFieldsPayeesPage extends BaseWebTest 
{
	@Test
	public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String errorHeader, String toolTip) throws Exception 
	{
    	KeywordManager.Execute(1, "Click main menu", "Home", "Menu", "Click", null);
		KeywordManager.Execute(2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		KeywordManager.Execute(3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(4, "Click add", "Payees", "Add", "Click", null);
		KeywordManager.Execute(5, "Click add again new dialog", "Payees_Add", "Add", "Click", null);
		KeywordManager.Execute(6, "Verify error header exist", "Payees_Add", "ErrorHeader", "VerifyExist", new String[] {"True"});
		KeywordManager.Execute(7, "Verify error header content", "Payees_Add", "ErrorHeader", "VerifyValue", new String[] {errorHeader});
		KeywordManager.Execute(8, "Verify error tooltip exist", "Payees_Add", "ToolTip", "VerifyExist", new String[] {"True"});
		KeywordManager.Execute(9, "Verify error tooltip content", "Payees_Add", "ToolTip", "VerifyValue", new String[] {toolTip});
		KeywordManager.Execute(10, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		KeywordManager.Execute(11, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		KeywordManager.Execute(12, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		KeywordManager.Execute(13, "Verify error tooltip", "Payees_Add", "ToolTip", "VerifyExistWithText", new String[] {"False",toolTip,"3"});
	}     
}
