package BaseElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseLabel extends BaseElement
{
	//Constructors
	public BaseLabel(String elementName, String searchBy, String searchValue) 
	{
		super(elementName, searchBy, searchValue);
	}
		
	public BaseLabel(String elementName, WebElement existingElement)
	{
		super(elementName, existingElement);
	}
	
	//Private Methods
//	private void Initialize() throws Exception 
//	{
//		FindElement();
//	}
	
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
