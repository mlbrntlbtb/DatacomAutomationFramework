package Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportHandler 
{
	private static ExtentReports extentReports;
	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentTest extentTest;
	
	public static void createExtentReport(String suiteName) 
	{
		extentSparkReporter = new ExtentSparkReporter(new File(ConfigHandler.extentOutputPath, "extent-reports.html").getPath());
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentSparkReporter.config().setReportName(suiteName);
	}
	
	public static void createExtentTest(String testName)
	{
		extentTest = extentReports.createTest(testName);
	}
	
	public static void createExtentInfo(String message) 
	{
		if(extentTest != null)
			extentTest.generateLog(Status.INFO, message);
	}
	
	public static void createExtentError(String message) 
	{
		if(extentTest != null)
			extentTest.generateLog(Status.FAIL, message);
	}
	
	public static void createExtentTestStatus(String testName, String status) 
	{	
		Status testStatus = null;
		switch(status.toLowerCase()) 
		{
			case "passed":
				testStatus = Status.PASS;
				break;
			case "failed":
				testStatus = Status.FAIL;
				break;
			case "skipped":
				testStatus = Status.SKIP;
				break;
		}
		extentTest.log(testStatus, testName);
	}
	
	public static void addScreenShotExtent(String filePath, String exceptionName) 
	{
		if(extentTest != null)
			extentTest.addScreenCaptureFromPath(filePath, exceptionName);
	}
	
	public static void flushExtentReport() 
	{
		extentReports.flush();
	}
}
