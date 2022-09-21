package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import Records.ConfigRecord;

public class ScreenshotHandler 
{
	public static void takeScreenshot(WebDriver driver) throws Exception
	{
		if(driver != null) 
		{
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String filePath = new File(ConfigRecord.screenShotPath, "EXCPTNIMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).toString() + ".png").getPath();
			File path = new File(filePath);
			FileUtils.copyFile(screenshot, path);
			LogHandler.info("See screenshot details here: [" + path + "]");
			Reporter.log("<br><a href ='" + path + "'><img src='" + path + "' height='400' width='400'/></a>");
			ExtentReportHandler.addScreenShotExtent(filePath, "Error Screenshot:");
		}
	}
}
