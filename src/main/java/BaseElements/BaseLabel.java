package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseLabel extends BaseElement
{
	//Constructors
	public BaseLabel(WebDriver driver, String elementName, String searchBy, String searchValue) 
	{
		super(driver, elementName, searchBy, searchValue);
	}
		
	public BaseLabel(String elementName, WebElement existingElement)
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
	
}
