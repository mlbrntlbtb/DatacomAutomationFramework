package BaseElements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import System.DriverManager;
import Utilities.*;

public class BaseElement 
{
	public WebElement Element;
	public String ElementName;
	public String SearchBy;
	public String SearchValue;
	public int DefaultWaitTime;;
	public boolean isExistingElement = false;
	
	//Constructor
	public BaseElement(String elementName, String searchBy, String searchValue) 
	{
		this.ElementName = elementName;
		this.SearchBy = searchBy;
		this.SearchValue = searchValue;
		this.DefaultWaitTime = 10;
	}
	
	public BaseElement(String elementName, WebElement existingElement) 
	{
		this.Element = existingElement;
		this.ElementName = elementName;
		this.DefaultWaitTime = 10;
		isExistingElement = true;
	}
	
	//Methods
	public By ElementByLocator(String searchBy, String searchValue) throws Exception 
	{
		switch(searchBy.toLowerCase()) 
		{
			case "id":
				return By.id(searchValue);
			case "classname":
				return By.className(searchValue);
			case "name":
				return By.name(searchValue);
			case "xpath":
				return By.xpath(searchValue);
			case "tagname":
				return By.tagName(searchValue);
			case "cssselector":
				return By.cssSelector(searchValue);
			case "linktext":
				return By.linkText(searchValue);
			case "partiallinktext":
				return By.partialLinkText(searchValue);
			default:
				throw new Exception("Invalid search type: [" + searchBy + "]");
		}
	}
	
	public By ElementByLocator() throws Exception
	{
		return ElementByLocator(SearchBy, SearchValue);
	}
	
	public void FindElement() throws Exception 
	{
		FindElement(DefaultWaitTime); //Set default wait time
	}
	
	
	public void FindElement(int specifiedWaitTime) throws Exception 
	{
		if(isExistingElement)
			return;
		
		try 
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.GetActiveDriver() , Duration.ofSeconds(specifiedWaitTime));
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(ElementByLocator()));
			LogHandler.info("Element [" + ElementName + "] found.");
		}
		catch(Exception e) 
		{
			throw new Exception("Element [" + ElementName + "] could not be found.");
		}
	}
	
	public boolean ElementExist() throws Exception 
	{
		return ElementExist(DefaultWaitTime);
	}
	
	public boolean ElementExist(int specifiedWaitTime) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.GetActiveDriver(), Duration.ofSeconds(specifiedWaitTime));
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(ElementByLocator()));
			LogHandler.info("Element [" + ElementName + "] found.");
			return true;
		}
		catch(Exception e) 
		{
			LogHandler.info("Element [" + ElementName + "] could not be found.");
			return false;
		}
	}
	
	public boolean ElementTextExist(String textValue) throws Exception 
	{
		return ElementTextExist(textValue, DefaultWaitTime);
	}
	
	public boolean ElementTextExist(String textValue, int specifiedWaitTime) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.GetActiveDriver(), Duration.ofSeconds(specifiedWaitTime));
			wait.ignoring(Exception.class);
			wait.until(ExpectedConditions.textToBePresentInElementValue(Element, textValue));
			return true;
		}
		catch(Exception e) 
		{
			LogHandler.info("Element [" + ElementName + "] could not be found.");
			return false;
		}
	}
	
	public WebElement GetChildElement(WebElement parentElement, String childSearchBy, String childLocatorValue, int specifiedWaitTime) throws Exception 
	{
		if(Element == null)
			FindElement();
		
		WebElement ChildElement = null;
		try 
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.GetActiveDriver(), Duration.ofSeconds(specifiedWaitTime));
			switch(childSearchBy.toLowerCase()) 
			{
				case "id":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.id(childLocatorValue))); 
				break;
				
				case "classname":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.className(childLocatorValue)));
				break;
				
				case "name":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.name(childLocatorValue)));
				break;
				
				case "xpath":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.xpath(childLocatorValue)));
				break;
				
				case "tagname":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.tagName(childLocatorValue)));
				break;
				
				case "cssselector":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.cssSelector(childLocatorValue)));;
				break;
				
				case "linktext":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.linkText(childLocatorValue)));
				break;
				
				case "partiallinktext":
					ChildElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, By.partialLinkText(childLocatorValue)));
				break;
				
				default:
					throw new Exception("Invalid search type: [" + childSearchBy + "]");
			}
			LogHandler.info("Child element with locator value: [" + childLocatorValue + "] found.");
		}
		catch(Exception e) 
		{
			throw new Exception("Child element with locator value: [" + childLocatorValue + "] could not be found.");
		}
		return ChildElement;
	}
	
	public List<WebElement> GetChildElements(String parentSearchBy, String parentSearchValue,  String childSearchBy, String childLocatorValue, int specifiedWaitTime) throws Exception 
	{
		if(Element == null)
			FindElement();
		
		List<WebElement> ChildElements = null;
		try 
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.GetActiveDriver(), Duration.ofSeconds(specifiedWaitTime));
			switch(childSearchBy.toLowerCase()) 
			{
				case "id":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.id(childLocatorValue))); 
				break;
				
				case "classname":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.className(childLocatorValue))); 
				break;
				
				case "name":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.name(childLocatorValue))); 
				break;
				
				case "xpath":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.xpath(childLocatorValue))); 
				break;
				
				case "tagname":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.tagName(childLocatorValue))); 
				break;
				
				case "cssselector":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.cssSelector(childLocatorValue))); 
				break;
				
				case "linktext":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.linkText(childLocatorValue))); 
				break;
				
				case "partiallinktext":
					ChildElements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ElementByLocator(parentSearchBy, parentSearchValue), By.partialLinkText(childLocatorValue))); 
				break;
				
				default:
					throw new Exception("Invalid search type: [" + SearchBy + "]");
			}
			LogHandler.info("Child elements with locator value: [" + childLocatorValue + "] found.");
		}
		catch(Exception e) 
		{
			throw new Exception("Child elements with locator value: [" + childLocatorValue + "] could not be found.");
		}
		return ChildElements;
	}

	public String GetValue() throws Exception 
	{
		if(Element == null)
			FindElement();
			
		String elementValue = "";
		if(Element.getText() != null) 
		{
			elementValue = Element.getText();
		}
		else if(Element.getAttribute("value") != null)
		{
			elementValue = Element.getAttribute("value");
		}
		else if(Element.getAttribute("innerText") != null)
		{
			elementValue = Element.getAttribute("innerText");
		}
		else if(Element.getAttribute("innerHTML") != null)
		{
			elementValue = Element.getAttribute("innerHTML");
		}
		else if(Element.getAttribute("checked") != null)
		{
			elementValue = Element.getAttribute("checked");
		}
		else if(Element.getAttribute("placeholder") != null)
		{
			elementValue = Element.getAttribute("placeholder");
		}
		else if(Element.getAttribute("title") != null)
		{
			elementValue = Element.getAttribute("title");
		}
		return elementValue.trim();
	}
	
	//Keywords
	public void WaitFindElement(String specifiedWaitTime) throws Exception 
	{
		LogHandler.info("Waiting for element: [" + ElementName + "]... ");
		FindElement(Integer.parseInt(specifiedWaitTime));
	}
	
	public void VerifyExist(String expectedValue) throws Exception 
	{
		LogHandler.info("Verifying element: [" + ElementName + "] exists... ");
		boolean actualValue = ElementExist();
		Assert.assertEquals(actualValue, Boolean.parseBoolean(expectedValue));
	}
	
	public void VerifyValue(String expectedValue) throws Exception 
	{
		FindElement();
		String actualValue = GetValue();
		LogHandler.info("Expected value: [" + expectedValue + "] Actual value: [" + actualValue + "]");
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	public void VerifyPartialValue(String partialValue) throws Exception 
	{
		FindElement();
		String actualValue = new BaseElement("Target Element",Element).GetValue();
		boolean partialMatch = actualValue.contains(partialValue);
		LogHandler.info("Expected value: [" + partialValue + "] Actual value: [" + actualValue + "]");
		
		if(!partialMatch)
			throw new Exception("Expected value does not partially matched actual value.");
	}
	
	public void AssignValueToVariable(String variableName) throws Exception 
	{
		FindElement();
		String actualValue = GetValue();
		LogHandler.info("Assigned value: [" + actualValue + "] to Variable name: [" + variableName + "]");
		VariableHandler.SetVariable(variableName, actualValue);
	}
}
