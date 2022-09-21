package Records;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import Utilities.ExceptionHandler;


public class ConfigRecord 
{
	public static String suiteName;
	public static String keepBrowserOpen;
	public static String projectPath;
	public static String configPath;
	public static String systemLogsPath;
	public static String productsPath;
	public static String targetProductPath;
	public static String testScriptsPath;
	public static String testSuitesPath;
	public static String testResultsPath;
	public static String pageObjectsPath;
	public static String testOutputPath;
	public static String screenShotPath;
    public static String logPath;
    public static String currentTestResultsPath;
	public static String extentOutputPath;
	
	
	public static synchronized void Initialize() throws Exception 
	{
		projectPath = System.getProperty("user.dir");
		configPath = new File(projectPath, "config").getPath();
		systemLogsPath = new File(projectPath, "system-logs").getPath();
    	productsPath = new File(projectPath, "products").getPath();
    	targetProductPath = new File(productsPath, GetProperty("product.name")).getPath();
    	testResultsPath = new File(targetProductPath, "test-results").getPath();
    	pageObjectsPath = new File(targetProductPath, "page-objects").getPath();
    	testOutputPath = new File(projectPath, "test-output").getPath();
    	extentOutputPath = new File(testOutputPath, "extentreports").getPath();
	}
	
	public static synchronized String GetProperty(String propertyName) throws Exception 
	{
		String propValue = "";
		try 
		{
			Properties prop = new Properties();
			InputStream config = new FileInputStream(new File(configPath, "config.properties").getPath());
			prop.load(config);
			propValue = prop.getProperty(propertyName);
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
		return propValue;
	}
	
	public static synchronized void SetProperty(String propertyName, String propertyValue) throws Exception 
	{
		try 
		{
			Properties prop = new Properties();
			OutputStream config = new FileOutputStream(new File(configPath, "config.properties").getPath());
			prop.setProperty(propertyName, propertyName);
			prop.store(config,null);
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static synchronized void RemoveProperty(String propertyName, String propertyValue) throws Exception 
	{
		try 
		{
			Properties prop = new Properties();
			InputStream config = new FileInputStream(new File(configPath, "config.properties").getPath());
			prop.load(config);
			prop.remove(propertyName, propertyName);
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
}
