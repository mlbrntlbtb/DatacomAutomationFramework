package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Utilities.LogHandler;

public class TestNGITestListener implements ITestListener
{
	
	
	public void onTestSuccess(ITestResult result) 
	{
		try 
		{
			LogHandler.statusTest("Passed");
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
		} 
		catch (Exception e) 
		{
			//
		}
	}

	public void onTestFailure(ITestResult result) 
	{
		try 
		{
			LogHandler.statusTest("Failed");
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
		} 
		catch (Exception e) 
		{
			//
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		try 
		{
			LogHandler.statusTest("Skipped");
			LogHandler.endTest(result.getTestClass().getRealClass().getSimpleName());
		} 
		catch (Exception e) 
		{
			//
		}
	}
	
	public void onStart(ITestContext context) 
	{	
		
	}

	public void onFinish(ITestContext context) 
	{
		
	}
	
	public void onTestStart(ITestResult result) 
	{
		try 
		{
			LogHandler.startTest(result.getTestClass().getRealClass().getSimpleName());
		}
		catch (Exception e) 
		{
			//
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		//
	}
}
