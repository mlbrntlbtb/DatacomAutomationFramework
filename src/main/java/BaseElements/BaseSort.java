package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseSort extends BaseElement
{
	//Private Variables -- add required values here
	private String ascendingValue = "IconChevronDownSolid";
	private String descendingValue = "IconChevronUpSolid";
	
	//Constructors
	public BaseSort(String elementName, String searchBy, String searchValue) 
	{
		super(elementName, searchBy, searchValue);
	}
	
	public BaseSort(String elementName, WebElement existingElement)
	{
		super(elementName, existingElement);
	}

	//Private Methods
	private void Initialize() throws Exception 
	{
		FindElement();
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
	public void VerifySortType(String expectedValue) throws Exception 
	{
		if(!expectedValue.equals("ascending") && !expectedValue.equals("descending")) 
			throw new Exception("Invalid expected value. Select expected sort values: [ascending, descending]");
			
		Initialize();
		
		String getSortType = Element.getAttribute("class").toLowerCase().trim();
		String actualSortValue;
		
		if(getSortType.contains(ascendingValue.toLowerCase())) 
		{
			actualSortValue = "ascending";
		}
		else if(getSortType.contains(descendingValue.toLowerCase()))
		{
			actualSortValue = "descending";
		}
		else
			throw new Exception("Element does not support sorting values");
		
		LogHandler.info("Expected value: [" + expectedValue + "] Actual value: [" + actualSortValue + "]");
		Assert.assertEquals(expectedValue, actualSortValue);
	}
	
	public void Click() throws Exception 
	{
		Initialize();
		LogHandler.info("Clicking element: [" + ElementName + "]... ");
		Element.click();
	}	
}
