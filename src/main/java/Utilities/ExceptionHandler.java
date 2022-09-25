package Utilities;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;

import System.DriverManager;

public class ExceptionHandler 
{	
	public ExceptionHandler(String exName, Exception ex) throws Exception 
	{
		Handle(ex, exName);
		throw ex;
	}
	
	public ExceptionHandler(String exName, Exception ex, WebDriver driver) throws Exception 
	{
		Handle(ex, exName);
		ScreenshotHandler.takeScreenshot(exName, driver);
		DriverManager.Quit();
		throw ex;
	}
	
	private void Handle(Exception exception, String exceptionName) throws Exception 
	{
		switch(exceptionName) 
		{
		 case "NoSuchElementException":
			 LogHandler.error("[" + exceptionName + "]: Element not found.");
		 	break;
		 	
		 case "StaleElementReferenceException":
			 LogHandler.error("[" + exceptionName + "]: Target element has been stale and no longer available.");
			 break;
			 
		 case "ElementNotInteractableException":
			 LogHandler.error("[" + exceptionName + "]: Element is not interactable.");
			 break;
			 
		 case "ElementClickInterceptedException":
			 LogHandler.error("[" + exceptionName + "]: Element cannot be clicked due to blocking of another element.");
			 break;
			 
		 case "NoSuchFrameException":
			 LogHandler.error("[" + exceptionName + "]: No frame found.");
			 break;
			 
		 case "NoAlertPresentException":
			 LogHandler.error("[" + exceptionName + "]: No present alert has been found on the page.");
			 break;
	     
		 case "NoSuchWindowException":
			 LogHandler.error("[" + exceptionName + "]: No window has been found.");
			 break;
			 
		 case "TimeoutException":
			 LogHandler.error("[" + exceptionName + "]: Locating the element or the page has reached the maximum time limit.");
			 break;
			 
		 case "NoSuchSessionException":
			 LogHandler.error("[" + exceptionName + "]: Web driver is not available or has been terminated.");
			 break;
		
		 case "WebDriverException":
			 LogHandler.error("[" + exceptionName + "]: Web driver is not available or has been terminated.");
			 break;
			 
		 case "InvocationTargetException":
			 LogHandler.error("[" + exceptionName + "]: Keyword has incorrect value or number of parameters.");
			 break;
			 
		 default:
			 LogHandler.error("[" + exceptionName + "]: Unexpected error has been encountered.");
			 break;
		}
		
		//LogHandler.error("See error details: [" + ExceptionUtils.getMessage(exception.getCause()) +"]");
		LogHandler.error("See error details: [" + ExceptionUtils.getStackTrace(exception.getCause()) +"]");
	}
}
