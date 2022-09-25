package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseToolTip extends BaseElement
{
	//Constructors
	public BaseToolTip(String elementName, String searchBy, String searchValue) 
	{
		super(elementName, searchBy, searchValue);
	}
	
	public BaseToolTip(String elementName, WebElement existingElement)
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
	public void VerifyExistWithText(String expectedValue, String textValue, String waitTime) throws Exception 
	{
		LogHandler.info("Verifying tooltip with text value: [" + textValue + "]... ");
		boolean isExistWithText = ElementTextExist(textValue, Integer.parseInt(waitTime));
		Assert.assertEquals(isExistWithText, Boolean.parseBoolean(expectedValue));
	}
}
