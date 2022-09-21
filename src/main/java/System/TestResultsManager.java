package System;

import java.io.File;

import org.apache.commons.io.FileUtils;

import Records.ConfigRecord;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class TestResultsManager 
{
	public static void getTestResults() throws Exception 
	{
		try 
		{
			LogHandler.info("For more details of [" + ConfigRecord.suiteName + "] execution, see test results here: [" + ConfigRecord.currentTestResultsPath + "]\n\n\n");
			LogHandler.close(); //Close log file before moving logs to test results path
			File source = new File(ConfigRecord.testOutputPath);
			File target = new File(ConfigRecord.currentTestResultsPath);
			FileUtils.copyDirectoryToDirectory(source, target);
		}
		catch(Exception ex) 
		{
			LogHandler.error("An error encountered while retrieving test results. [" + ex.getClass().getSimpleName() + "]");
			LogHandler.error("See error details: [" + ex.getMessage() + "]");
			new ExceptionHandler(ex.getClass().getSimpleName(), ex);
		}
	}
}
