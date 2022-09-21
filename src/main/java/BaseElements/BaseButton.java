package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseButton extends BaseElement
{
	//Constructors
	public BaseButton(WebDriver driver, String elementName, String searchBy, String searchValue) 
	{
		super(driver, elementName, searchBy, searchValue);
	}
	
	public BaseButton(String elementName, WebElement existingElement)
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
	
	public void VerifyPartialValue(String partialValue) throws Exception 
	{
		super.VerifyPartialValue(partialValue);
	}
	
	//Keywords
	public void Click() throws Exception 
	{
		try 
		{
			Initialize();
			
			LogHandler.info("Clicking element: [" + ElementName + "]... ");
			Element.click();
			LogHandler.info("Click() passed.");
		}
		catch(Exception e) 
		{
			LogHandler.error("Click() failed.");
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
}
