package Utilities;

import org.testng.annotations.*;

public class DataProviderHandler 
{
	private static String fileName;
	private static String fileSheet;
	
	public static void SetFile(String FileName, String FileSheet) 
	{
		fileName = FileName;
		fileSheet = FileSheet;
	}
	@DataProvider(name="dataProvider")
	public Object[][] DataProvider() throws Exception
	{
		TestDataHandler.SetFile(fileName, fileSheet);
		return TestDataHandler.GetDataProvider();
	}
}
