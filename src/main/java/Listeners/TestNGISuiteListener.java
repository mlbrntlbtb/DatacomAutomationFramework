package Listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import Records.ConfigRecord;
import System.*;
import Utilities.LogHandler;

public class TestNGISuiteListener implements ISuiteListener
{
	@Override
    public void onStart(ISuite suite) 
	{
        try 
        {
        	ConfigRecord.suiteName = suite.getName();
			LogHandler.startSuite(suite.getName());
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
    }

    @Override
    public void onFinish(ISuite suite) 
    {
    	try 
        {
			LogHandler.endSuite(suite.getName());
			TestResultsManager.getTestResults();
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
    }
}
