package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import Utilities.LogHandler;

public class TestNGITestListener implements ITestListener
{
	public void onTestStart(ITestResult result) 
	{
		try 
		{
			LogHandler.startTest(result.getTestClass().getRealClass().getSimpleName());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		try 
		{
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
			LogHandler.statusTest(result.getTestClass().getRealClass().getSimpleName(), "Passed");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) 
	{
		try 
		{
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
			LogHandler.statusTest(result.getTestClass().getRealClass().getSimpleName(), "Failed");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		try 
		{
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
			LogHandler.statusTest(result.getTestClass().getRealClass().getSimpleName(), "Skipped");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void onStart(ITestContext context) 
	{	
		
	}

	public void onFinish(ITestContext context) 
	{
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}
}
