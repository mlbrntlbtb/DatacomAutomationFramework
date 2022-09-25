package TestCases.WebTestCases;

import org.testng.annotations.*;

import BaseTest.BaseWebTest;
import System.KeywordManager;
import Utilities.DataProviderHandler;

public class TC4_VerifySortOrderPayeeName extends BaseWebTest
{
	@Test
	public void test(String menu, String payeeName, String creditCard1st, String creditCard2nd, String sortAsc, String sortDesc) throws Exception 
	{
		KeywordManager.Execute(1, "Click main menu", "Home", "Menu", "Click", null);
		KeywordManager.Execute(2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		KeywordManager.Execute(3, "Click add", "Payees", "Add", "Click", null);
		KeywordManager.Execute(4, "Select Payee Name", "Payees_Add", "PayeeName", "SetSelectValue", new String[]{payeeName});
		KeywordManager.Execute(5, "Enter Payee credit card details first 12 digits", "Payees_Add", "PayeeCreditCard_1st_Field", "SetValue", new String[]{creditCard1st});
		KeywordManager.Execute(6, "Enter Payee credit card details last 4 digits", "Payees_Add", "PayeeCreditCard_2nd_Field", "SetValue", new String[]{creditCard2nd});
		KeywordManager.Execute(7, "Click add", "Payees_Add", "Add", "Click", null);
		KeywordManager.Execute(8, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{sortAsc});
		KeywordManager.Execute(9, "Click payee name sort", "Payees", "Sort_Name", "Click", null);
		KeywordManager.Execute(10, "Verify payee name sort type", "Payees", "Sort_Name", "VerifySortType", new String[]{sortDesc});
	}  
}
