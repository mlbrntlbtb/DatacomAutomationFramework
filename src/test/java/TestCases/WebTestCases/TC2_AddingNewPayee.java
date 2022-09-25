package TestCases.WebTestCases;

import org.testng.annotations.*;
import BaseTest.BaseWebTest;
import System.KeywordManager;
import Utilities.*;

public class TC2_AddingNewPayee extends BaseWebTest
{
	@Test
	public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String successAddMsg) throws Exception 
	{
		KeywordManager.Execute(1, "Click main menu", "Home", "Menu", "Click", null);
		KeywordManager.Execute(2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		KeywordManager.Execute(3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(4, "Click add", "Payees", "Add", "Click", null);
		KeywordManager.Execute(5, "Verify Add Payees page has loaded", "Payees_Add", "PayeeName", "VerifyExist", new String[]{"True"});
		KeywordManager.Execute(6, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		KeywordManager.Execute(7, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		KeywordManager.Execute(8, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		KeywordManager.Execute(9, "Click add", "Payees_Add", "Add", "Click", null);
		KeywordManager.Execute(10, "Verify message 'Payee added' is displayed", "Alert", "Alert", "VerifyValue", new String[]{successAddMsg});
	}	  	 
}
