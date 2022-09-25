package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseTextBox extends BaseElement
{
	//Constructors
	public BaseTextBox(String elementName, String searchBy, String searchValue) 
	{
		super(elementName, searchBy, searchValue);
	}
	
	public BaseTextBox(String elementName, WebElement existingElement) 
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
	public void SetValue(String value) throws Exception 
	{
		Initialize();
		LogHandler.info("Setting value: [" + value + "] on element: [" + ElementName + "]... ");
		Element.clear();
		Element.sendKeys(value);
	}
}
