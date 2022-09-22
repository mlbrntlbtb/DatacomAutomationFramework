package BaseElements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class BaseComboBox extends BaseElement
{
		//Private Variables -- add more locators required here
		private static String comboBoxListItems_XPath = ".//div[@role='listbox']//a[@role='option']//span[@class='text']";
	
		//Constructors
		public BaseComboBox(WebDriver driver, String elementName, String searchBy, String searchValue) 
		{
			super(driver, elementName, searchBy, searchValue);
		}
		
		public BaseComboBox(String elementName, WebElement existingElement)
		{
			super(elementName, existingElement);
		}

		//Private Methods
		private void Initialize() throws Exception 
		{
			FindElement();
		}
		
		private WebElement GetInputField() throws Exception 
		{
			if(Element == null) 
				FindElement();
			
			return GetChildElement(Element,"tagname","input", 10);
		}
		
		private List<WebElement> GetComboBoxListItems() throws Exception
		{
			if(Element == null) 
				FindElement();
			
			return GetChildElements(SearchBy, SearchValue, "xpath",comboBoxListItems_XPath, 10);
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
		public void SetSelectValue(String value) throws Exception 
		{
			try 
			{
				Initialize();
				WebElement inputField = GetInputField();
				LogHandler.info("Setting value: [" + value + "] to input field... ");
				inputField.clear();
				inputField.sendKeys(value);
				
				List<WebElement> comboBoxItems = GetComboBoxListItems();
				boolean itemFound = false;
				for(WebElement comboBoxItem : comboBoxItems) 
				{
					String comboBoxItemValue = new BaseElement("ComboBox Item", comboBoxItem).GetValue();
					if(comboBoxItemValue.equalsIgnoreCase(value)) 
					{
						comboBoxItem.click();
						LogHandler.info("Selecting value: [" + value + "] to items list... ");
						itemFound = true;
						break;
					}
				}
				if(!itemFound)
					throw new Exception("Item with value: [" + value + "] not found.");
				
				LogHandler.info("SetSelectValue() passed.");
			}
			catch(Exception e) 
			{
				LogHandler.error("SetSelectValue() failed.");
				new ExceptionHandler(e.getClass().getSimpleName(), e);
			}
		}
}
