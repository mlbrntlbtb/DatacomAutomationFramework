package BaseElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import Utilities.VariableHandler;

public class BaseThumbnail extends BaseElement 
{
	//Private Variables
	private String thumbnailItems_XPath = ".//div[contains(@data-rv-id,'account:id')]";
	private String thumbnailItemsTitle_XPath = ".//h3[@title]";
	private String thumbnailItemsBalance_XPath = ".//span[@class='account-balance']";
	
	//Constructors
	public BaseThumbnail(WebDriver driver, String elementName, String searchBy, String searchValue) 
	{
		super(driver, elementName, searchBy, searchValue);
	}
	
	public BaseThumbnail(String elementName, WebElement existingElement)
	{
		super(elementName, existingElement);
	}

	//Private Methods
	private void Initialize() throws Exception 
	{
		FindElement();
	}
	
	private List<WebElement> GetThumbnailItems() throws Exception 
	{
		if(Element == null) 
			FindElement();
		
		return GetChildElements(SearchBy, SearchValue, "xpath", thumbnailItems_XPath, 10);
	}
	
	private double performMathOperation(String operation, String previousValue, String inputValue) throws Exception 
	{
		double prevValue = Double.parseDouble(previousValue.replaceAll(",", ""));
		double inpValue = Double.parseDouble(inputValue);
		double newValue;
		
		switch(operation) 
		{
			case "add":
				 newValue = prevValue + inpValue;
				 break;
			case "subtract":
				newValue = prevValue - inpValue;
				break;
			case "multiply":
				newValue = prevValue * inpValue;
				break;
			case "divide":
				newValue = prevValue / inpValue;
				break;
			default:
				throw new Exception("Invalid math operation.");
		}
		return newValue;
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
	
	public void VerifyPartialValue(String partialValue) throws Exception 
	{
		super.VerifyPartialValue(partialValue);
	}
	
	//Keywords
	public void GetThumbnailBalance(String title, String variableName) throws Exception 
	{
		try 
		{
			Initialize();
			
			LogHandler.info("Getting thumbnail balance from title: [" + title + "]... ");
			List<WebElement> thumbnailItems = GetThumbnailItems();
			boolean tFound = false;
			for(WebElement thumbnail : thumbnailItems) 
			{
				String thumbnailTitle = thumbnail.findElement(By.xpath(thumbnailItemsTitle_XPath)).getText().trim();
				
				if(thumbnailTitle.equalsIgnoreCase(title))
				{
					String thumbnailBalance = thumbnail.findElement(By.xpath(thumbnailItemsBalance_XPath)).getText().trim();
					LogHandler.info("Assigned value: [" + thumbnailBalance + "] to Variable name: [" + variableName + "]");
					VariableHandler.SetVariable(variableName, thumbnailBalance);
					tFound = true;
					break;
				}
			}
			if(!tFound)
				throw new Exception("Thumbnail item with title: [" + title + "] not found on the list.");
			
			LogHandler.info("GetThumbnailBalance() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("GetThumbnailBalance() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public void VerifyThumbnailBalance(String title, String previousValue, String operation, String inputValue) throws Exception 
	{
		try 
		{
			Initialize();
			
			LogHandler.info("Getting thumbnail balance from title: [" + title + "]... ");
			LogHandler.info("Computing thumbnail balance from previous value: [" + 	previousValue + "] [" + operation + "] [" + inputValue + "]");
			
			List<WebElement> thumbnailItems = GetThumbnailItems();
			boolean tFound = false;
			Double actualValue = null;
			Double expectedValue = performMathOperation(operation,previousValue,inputValue);
			for(WebElement thumbnail : thumbnailItems) 
			{
				String thumbnailTitle = thumbnail.findElement(By.xpath(thumbnailItemsTitle_XPath)).getText().trim();
				String thumbnailBalance = thumbnail.findElement(By.xpath(thumbnailItemsBalance_XPath)).getText().trim();
				
				if(thumbnailTitle.equalsIgnoreCase(title))
				{
					actualValue = Double.parseDouble(thumbnailBalance.replaceAll(",",""));
					
					LogHandler.info("Expected value: [" + expectedValue + "] Actual value: [" + actualValue + "]");
					tFound = true;
					break;
				}
			}
			if(!tFound)
				throw new Exception("Thumbnail item with title: [" + title + "] not found on the list.");
			
			Assert.assertEquals(actualValue, expectedValue);
			LogHandler.info("GetThumbnailBalance() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("GetThumbnailBalance() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}		
}
