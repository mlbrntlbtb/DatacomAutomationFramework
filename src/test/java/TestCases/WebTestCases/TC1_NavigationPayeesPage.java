package TestCases.WebTestCases;

import org.testng.annotations.*;
import BaseTest.BaseWebTest;
import System.*;

public class TC1_NavigationPayeesPage extends BaseWebTest
{
	  @Test
	  public void test(String menu) throws Exception 
	  {
		  KeywordManager.Execute(1, "Click main menu", "Home", "Menu", "Click", null);
		  KeywordManager.Execute(2, "Select Payees menu", "MainMenu", "List_Menu", "SelectListItem", new String[]{menu});
		  KeywordManager.Execute(3, "Verify Payees page has loaded", "Payees", "Title", "VerifyExist", new String[]{"True"});
	  }	 
}
