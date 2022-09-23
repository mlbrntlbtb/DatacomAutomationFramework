package Listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import Utilities.*;

public class TestNGISuiteListener implements ISuiteListener
{
	@Override
    public void onStart(ISuite suite) 
	{
        try 
        {
        	ConfigHandler.suiteName = suite.getName();
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
			TestResultsHandler.getTestResults();
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
    }
}
