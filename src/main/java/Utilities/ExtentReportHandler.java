package Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportHandler 
{
	private static ExtentReports extentReports;
	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentTest extentTest;
	private static ThreadLocal<ExtentTest> safeExtentTest = new ThreadLocal<ExtentTest>();
	
	public static void createExtentReport(String suiteName) 
	{
		extentSparkReporter = new ExtentSparkReporter(new File(ConfigHandler.extentOutputPath, "extent-reports.html").getPath());
		extentReports = new ExtentReports();
		extentSparkReporter.config().setDocumentTitle("[" + suiteName + "] Test Results");
		extentSparkReporter.config().setReportName(suiteName);
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("User", System.getProperty("user.name"));
	}
	
	public static void createExtentTest(String testName)
	{
		extentTest = extentReports.createTest(testName);
		safeExtentTest.set(extentTest);
	}
	
	public static void createExtentInfo(String message) 
	{
		if(safeExtentTest.get() != null)
			safeExtentTest.get().log(Status.INFO, message);
	}
	
	public static void createExtentError(String message) 
	{
		if(safeExtentTest.get() != null)
			safeExtentTest.get().log(Status.FAIL, message);
	}
	
	public static void createExtentTestStatus(String status, String message) 
	{	
		if(safeExtentTest.get() != null) 
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
			safeExtentTest.get().log(testStatus, message);
		}
	}
	
	public static void addScreenShotExtent(String filePath, String exceptionName) 
	{
		if(safeExtentTest.get() != null)
			safeExtentTest.get().log(Status.FAIL, exceptionName, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
	}
	
	public static void flushExtentReport() 
	{
		extentReports.flush();
	}
}
