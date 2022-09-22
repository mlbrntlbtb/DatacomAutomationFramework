package BaseElements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseList extends BaseElement
{
	//Private Variables -- add required locators here
	String listItems_XPath = ".//li[contains(@class,'menu-primary-option')] | .//li//button[@data-monitoring-label]";
	
	//Constructors
	public BaseList(WebDriver driver, String elementName, String searchBy, String searchValue) 
	{
		super(driver, elementName, searchBy, searchValue);
	}
	
	public BaseList(String elementName, WebElement existingElement)
	{
		super(elementName, existingElement);
	}	
		
	//Private Methods
	private void Initialize() throws Exception 
	{
		FindElement();
	}
	
	private List<WebElement> GetListItems() throws Exception 
	{
		if(Element == null) 
			FindElement();
		
		return GetChildElements(SearchBy, SearchValue, "xpath", listItems_XPath, 10);
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
	
	public void SelectItem(String itemValue, boolean partial) throws Exception 
	{
		Initialize();
		List<WebElement> listItems = GetListItems();
		
		LogHandler.info("Finding list item: [" + itemValue + "]... ");
		boolean itemExist = false;
		for(WebElement listItem: listItems) 
		{
			String listItemValue = new BaseElement("List item", listItem).GetValue();
			
			if(!partial) 
			{
				if(listItemValue.equalsIgnoreCase(itemValue)) 
				{
					listItem.click();
					itemExist = true;
					LogHandler.info("List item: [" + itemValue + "] found. Selecting... ");
					break;
				}
			}
			else 
			{
				if(listItemValue.toLowerCase().contains(itemValue.toLowerCase())) 
				{
					listItem.click();
					itemExist = true;
					LogHandler.info("Partial value from list item: [" + itemValue + "] found. Selecting... ");
					break;
				}
			}
		}
		
		if(!itemExist)
			throw new Exception("List item: [" + itemValue + "] not found.");
	}
	
	//Keywords
	public void SelectListItem(String itemValue) throws Exception 
	{
		try 
		{
			SelectItem(itemValue, false);
			LogHandler.info("SelectListItem() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("SelectListItem() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void SelectPartialItemValue(String partialValue) throws Exception 
	{
		try 
		{
			SelectItem(partialValue, true);
			LogHandler.info("SelectPartialItemValue() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("SelectPartialItemValue() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
}
