package BaseElements;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import System.DriverManager;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseTab extends BaseElement
{
	//Private Variables
	private String tabItems_XPath = ".//li[@role='tab']";
	//Constructors
	public BaseTab(String elementName, String searchBy, String searchValue) 
	{
		super(elementName, searchBy, searchValue);
	}
	
	public BaseTab(String elementName, WebElement existingElement)
	{
		super(elementName, existingElement);
	}

	//Private Methods
	private void Initialize() throws Exception 
	{
		FindElement();
	}
	
	private List<WebElement> GetTabItems() throws Exception 
	{
		if(Element == null) 
			FindElement();
		
		return GetChildElements(SearchBy, SearchValue, "xpath", tabItems_XPath, 10);
	}
	
	//Public Methods
	public void WaitFindElement(String specifiedWaitTime) throws Exception 
	{
		super.WaitFindElement(specifiedWaitTime);
	}
	
	public void VerifyExist(String expectedValue) throws Exception 
	{
		super.VerifyExist(expectedValue);
	}
	
	public void VerifyValue(String expectedValue) throws Exception 
	{
		super.VerifyValue(expectedValue);
	}
	
	//Keywords
	public void SelectTabItem(String itemValue) throws Exception 
	{
		Initialize();
		List<WebElement> listItems = GetTabItems();
		
		LogHandler.info("Finding tab item: [" + itemValue + "]... ");
		boolean itemExist = false;
		for(WebElement listItem: listItems) 
		{
			String listItemValue = new BaseElement("Tab item", listItem).GetValue();
			if(listItemValue.equalsIgnoreCase(itemValue)) 
			{
				//listItem.click(); //Other elements receives the click -- try JS
				JavascriptExecutor js = (JavascriptExecutor)DriverManager.GetActiveDriver();
				js.executeScript("arguments[0].click()", listItem);
				itemExist = true;
				LogHandler.info("Tab item: [" + itemValue + "] found. Selecting... ");
				break;
			}
		}
		
		if(!itemExist)
			throw new Exception("Tab item: [" + itemValue + "] not found.");
	}
}
