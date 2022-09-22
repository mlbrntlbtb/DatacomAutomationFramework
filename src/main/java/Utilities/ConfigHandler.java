package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigHandler 
{
	public static String suiteName;
	public static String keepBrowserOpen;
	public static String projectPath;
	public static String configPath;
	public static String systemLogsPath;
	public static String productsPath;
	public static String targetProductPath;
	public static String testResultsPath;
	public static String pageObjectsPath;
	public static String userTestDataPath;
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
    	targetProductPath = new File(productsPath, GetProperty("config","product.name")).getPath();
    	testResultsPath = new File(targetProductPath, "test-results").getPath();
    	pageObjectsPath = new File(targetProductPath, "page-objects").getPath();
    	userTestDataPath = new File(targetProductPath, "user-test-data").getPath();
    	testOutputPath = new File(projectPath, "test-output").getPath();
    	extentOutputPath = new File(testOutputPath, "extentreports").getPath();
	}
	
	public static synchronized String GetProperty(String fileName, String propertyName) throws Exception 
	{
		String propValue = "";
		try 
		{
			Properties prop = new Properties();
			InputStream input = new FileInputStream(new File(configPath, fileName + ".properties").getPath());
			prop.load(input);
			propValue = prop.getProperty(propertyName);
			input.close();
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
		return propValue;
	}
	
	public static synchronized void SetProperty(String fileName, String propertyName, String propertyValue) throws Exception 
	{
		try 
		{	
			String filePath = new File(configPath, fileName + ".properties").getPath();	
			Properties prop = new Properties();
			InputStream input = new FileInputStream(filePath);
			prop.load(input);
			prop.setProperty(propertyName, propertyValue);
			OutputStream output = new FileOutputStream(filePath);
			prop.store(output, null);
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static synchronized void RemoveProperty(String fileName, String propertyName, String propertyValue) throws Exception 
	{
		try 
		{
			Properties prop = new Properties();
			InputStream input = new FileInputStream(new File(configPath, fileName + ".properties").getPath());
			prop.load(input);
			prop.remove(propertyName, propertyValue);
			input.close();
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
}
