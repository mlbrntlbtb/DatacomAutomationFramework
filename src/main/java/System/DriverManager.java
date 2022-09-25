package System;

import java.net.URL;
import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import Utilities.ConfigHandler;
import Utilities.ExceptionHandler;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager 
{
	private static WebDriver driver;
	private static ThreadLocal<WebDriver> safeDriver = new ThreadLocal<WebDriver>();
	private static String applicationURL;
	public static void Initialize(String browserName) throws Exception
	{
		try 
		{
			String remoteHost = ConfigHandler.GetProperty("config","remote.host");
			
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
			safeDriver.set(driver);
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static WebDriver GetActiveDriver() 
	{
		return safeDriver.get();
	}
	
	public static void LoadApplication(String url) throws Exception
	{
		if(safeDriver.get() != null) 
		{
			applicationURL = url;
			safeDriver.get().manage().window().maximize();
			safeDriver.get().get(url);
			safeDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			WaitApplicationReady(safeDriver.get());
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
			wait++;
		}
	}
	
	public static void Close() throws Exception 
	{
		if (safeDriver.get() != null) 
		{
			safeDriver.get().close();
		}
	}
	
	public static void Quit() throws Exception 
	{
		if (safeDriver.get() != null) 
		{
			safeDriver.get().quit();
		}
	}
	
	public static boolean DriverExist() 
	{
		return safeDriver.get() != null;
	}
	
	public static String BrowserName() throws Exception 
	{
		if(safeDriver.get() != null) 
		{
			Capabilities capabilities = ((RemoteWebDriver)safeDriver.get()).getCapabilities();
			String browserName = StringUtils.capitalize(capabilities.getBrowserName());
			return browserName;
		}
		return null;
	}
	
	public static String BrowserVersion() throws Exception 
	{
		if(safeDriver.get() != null) 
		{
			Capabilities capabilities = ((RemoteWebDriver)safeDriver.get()).getCapabilities();
			String browserVersion = capabilities.getBrowserVersion();
			return browserVersion;
		}
		return null;
	}
	
	public static String ApplicationUrl() throws Exception 
	{
		return applicationURL;
	}
}
