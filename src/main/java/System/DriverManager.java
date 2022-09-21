package System;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import Records.ConfigRecord;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager 
{
	private static WebDriver driver;
	public static WebDriver Initialize(String browserName) throws Exception
	{
		try 
		{
			LogHandler.info("Creating browser instance: [" + browserName + "]");
			String remoteHost = ConfigRecord.GetProperty("remote.host");
			
			switch(browserName.toLowerCase()) 
			{
				case "chrome":
					driver = WebDriverManager.chromedriver().create();
					break;
				case "firefox":
					driver = WebDriverManager.firefoxdriver().create();
					break;
				case "edge":
					driver = WebDriverManager.edgedriver().create();
					break;
				case "remote-chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					driver = new RemoteWebDriver(new URL(remoteHost),chromeOptions);
					break;
				case "remote-firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					driver = new RemoteWebDriver(new URL(remoteHost),firefoxOptions);
					break;
				case "remote-edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					driver = new RemoteWebDriver(new URL(remoteHost),edgeOptions);
					break;
				default:
					throw new Exception("Browser: [" + browserName + "] does not exist.");
			}
			
			//Get browser name and version
			Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
			String browserType = capabilities.getBrowserName();
			String browserVersion = capabilities.getBrowserVersion();
			
			LogHandler.info("Browser: [" + browserType + "] with Version: [" + browserVersion + "] created.");
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
		return driver;
	}
	
	public static WebDriver GetActiveDriver() 
	{
		return driver;
	}
	
	public static void LoadApplication(WebDriver driver, String url) throws Exception
	{
		if(driver != null) 
		{
			LogHandler.info("Loading target application: [" + url + "]... ");
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			driver.navigate().to(url);
			WaitApplicationReady(driver);
		}
	}
	
	public static void WaitApplicationReady(WebDriver driver) throws Exception 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		int waitLimit = 60;
		int wait = 0;
		
		while(wait < waitLimit && (!jse.executeScript("return document.readyState").equals("complete"))
				&& (!jse.executeScript("return window.jQuery != undefined && jQuery.active==0").equals("true"))) 
		{
			Thread.sleep(1000);
			LogHandler.info("Waiting for the application to load... ");
			wait++;
		}
	}
	
	public static void Close(WebDriver driver) throws Exception 
	{
		if (driver != null) 
		{
			LogHandler.info("Closing browser session... ");
			driver.close();
		}
	}
	
	public static void Quit(WebDriver driver) throws Exception 
	{
		if (driver != null) 
		{
			LogHandler.info("Terminating browser instance... ");
			driver.quit();
			driver = null;
		}
	}
}
