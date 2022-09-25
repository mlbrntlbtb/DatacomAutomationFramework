package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.*;
import org.testng.ITestResult;
import org.testng.Reporter;

import System.DriverManager;
import System.RequestSpecificationManager;

public class LogHandler 
{
	private static Logger log;
	private static RollingFileAppender appender;
	private static ConsoleAppender console;
	private static String dateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()).toString();
	private static boolean initFlag = false;
	
	private static void initialize() throws Exception 
	{
		if(!initFlag) 
		{
			ConfigHandler.Initialize();
			
			//Configuration for Log4j
			log = Logger.getLogger(LogHandler.class);
			appender = new RollingFileAppender();
			log.setLevel(Level.TRACE);
			appender.setAppend(true);
			appender.setMaxFileSize("10MB");
			appender.setMaxBackupIndex(1);
			
			String folderDateTimeName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
			String updatedTestFolderPath = new File(ConfigHandler.systemLogsPath, folderDateTimeName).getPath();
			
			if(ConfigHandler.suiteName == null) //Check if suite has been initialized as argument
				updatedTestFolderPath = new File(ConfigHandler.systemLogsPath, folderDateTimeName).getPath();
			else 
				updatedTestFolderPath = new File(ConfigHandler.testResultsPath + "\\" + ConfigHandler.suiteName, folderDateTimeName).getPath();
			
			ConfigHandler.screenShotPath = updatedTestFolderPath;
			ConfigHandler.currentTestResultsPath = updatedTestFolderPath;
			
			appender.setFile(new File(updatedTestFolderPath, "LOG_" + folderDateTimeName + ".log").getPath());
			appender.activateOptions();
			
			console = new ConsoleAppender();
			console.setTarget("System.out");
			console.activateOptions();
			
			PatternLayout layout = new PatternLayout();
			layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} [LOGS][%p] - %m%n");
			
			appender.setLayout(layout);
			console.setLayout(layout);

			log.addAppender(appender);
			log.addAppender(console);
			
			initFlag = true;
		}
	}
	
	public static void info (String message) throws Exception 
	{
		initialize();
		log.info(message);
		Reporter.log(dateTime + " [LOGS][INFO]: " + message);
		ExtentReportHandler.createExtentInfo(message.replace("\n","<br>"));
	}
	
	public static void error (String message) throws Exception 
	{
		initialize();
		log.error(message);
		Reporter.log(dateTime + " [LOGS][ERROR]: " + message);
		ExtentReportHandler.createExtentError(message);
	}
	
	public static void close() 
	{
		log.removeAllAppenders();
		appender.close();
		console.close();
		initFlag = false;
	}
	
	public static void startSuite (String message) throws Exception 
	{
		initialize();
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("Test suite: [" + message + "] has started... ");
		Reporter.log(dateTime + " [LOGS][INFO]: Test suite: [" + message + "] has started... ");
		ExtentReportHandler.createExtentReport(message);
	}
	
	public static void endSuite (String message) throws Exception 
	{
		initialize();
		log.info("Test suite: [" + message + "] has ended.");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		Reporter.log(dateTime + " [LOGS][INFO]: Test suite: [" + message + "] has ended.");
		ExtentReportHandler.flushExtentReport();
	}
	
	public static void startTest (String testName) throws Exception 
	{
		initialize();
		String startMessage = "Test: [" + testName + "] has started... ";
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info(startMessage);
		Reporter.log(dateTime + " [LOGS][INFO]: " + startMessage);
		ExtentReportHandler.createExtentTest(testName);
		ExtentReportHandler.createExtentInfo(startMessage);
		
		//Include for Web tests
		startBrowser();
		startURL();
		
		//Include for API tests
		startRequest();
	}
	
	public static void endTest (String testName) throws Exception 
	{
		initialize();
		String endMessage = "Test: [" + testName + "] has ended... ";
		log.info(endMessage);
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		Reporter.log(dateTime + " [LOGS][INFO]: " + endMessage);
		ExtentReportHandler.createExtentInfo(endMessage);
		
		//Include for Web tests
		endBrowser();
	}
	
	public static void statusTest (String testName, String status) throws Exception 
	{
		initialize();
		String statusMessage = "Test: [" + testName + "] Status: [" + status + "]";
		log.info("****************************************************************************************");
		log.info(statusMessage);
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		Reporter.log(dateTime + " [LOGS][INFO]: " + statusMessage);
		ExtentReportHandler.createExtentTestStatus(status, statusMessage);
	}
	
	public static void executeStep(int stepIndex, String description, String keyword, String[] parameters) throws Exception 
	{
		initialize();
		String stepMessage = "Test step: [" + stepIndex + "] - [" + description + "] has started... ";
		String keywordMessage;
		if(parameters != null) 
		{
			String paramMessage = null;
			for(String param : parameters) 
			{
				paramMessage = paramMessage != null ? paramMessage + " | [" + param + "]" : "[" + param + "]";
			}
			keywordMessage = "Executing keyword: [" + keyword + "] with parameters: " + paramMessage + "... ";
		}
		else
			keywordMessage = "Executing keyword: [" + keyword + "]... ";
		
		log.info("****************************************************************************************");
		log.info(stepMessage);
		log.info("****************************************************************************************");
		log.info(keywordMessage);
		Reporter.log(dateTime + " [LOGS][INFO]: " + stepMessage);
		Reporter.log(dateTime + " [LOGS][INFO]: " + keywordMessage);
		ExtentReportHandler.createExtentInfo(stepMessage);
		ExtentReportHandler.createExtentInfo(keywordMessage);
	}
	
	public static void executeStep(int stepIndex, String description, String keyword) throws Exception 
	{
		initialize();
		String stepMessage = "Test step: [" + stepIndex + "] - [" + description + "] has started... ";
		String keywordMessage = "Executing keyword: [" + keyword + "]... ";
		
		log.info("****************************************************************************************");
		log.info(stepMessage);
		log.info("****************************************************************************************");
		log.info(keywordMessage);
		Reporter.log(dateTime + " [LOGS][INFO]: " + stepMessage);
		Reporter.log(dateTime + " [LOGS][INFO]: " + keywordMessage);
		ExtentReportHandler.createExtentInfo(stepMessage);
		ExtentReportHandler.createExtentInfo(keywordMessage);
	}
	
	public static void startBrowser() throws Exception 
	{
		if(DriverManager.DriverExist()) 
		{
			initialize();
			String browserName = DriverManager.BrowserName();
			String browserVersion = DriverManager.BrowserVersion();
			String startMessage = "Browser: [" + browserName + "] Version: [" + browserVersion + "] detected.";
			log.info(startMessage);
			Reporter.log(dateTime + " [LOGS][INFO]: " + startMessage);
			ExtentReportHandler.createExtentInfo(startMessage);	
		}
	}
	
	public static void startURL() throws Exception 
	{
		if(DriverManager.DriverExist()) 
		{
			initialize();
			String applicationURL = DriverManager.ApplicationUrl();
			String startMessage = "Application URL: [" + applicationURL + "] loaded.";
			log.info(startMessage);
			Reporter.log(dateTime + " [LOGS][INFO]: " + startMessage);
			ExtentReportHandler.createExtentInfo(startMessage);	
		}
	}
	
	public static void endBrowser() throws Exception 
	{
		if(DriverManager.DriverExist()) 
		{
			initialize();
			String browserName = DriverManager.BrowserName();
			String browserVersion = DriverManager.BrowserVersion();
			String startMessage = "Browser: [" + browserName + "] Version: [" + browserVersion + "] terminated.";
			log.info(startMessage);
			Reporter.log(dateTime + " [LOGS][INFO]: " + startMessage);
			ExtentReportHandler.createExtentInfo(startMessage);	
		}
	}
	
	public static void startRequest() throws Exception 
	{
		if(RequestSpecificationManager.RequestExist()) 
		{
			initialize();
			String baseUrl = RequestSpecificationManager.GetBaseURL();
			String startMessage = "Request with Base URL: [" + baseUrl + "] detected.";
			log.info(startMessage);
			Reporter.log(dateTime + " [LOGS][INFO]: " + startMessage);
			ExtentReportHandler.createExtentInfo(startMessage);	
		}
	}
}
