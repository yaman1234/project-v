package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilBase {
	
	// global driver(s) initialization, visible to child classes
	protected static WebDriver driver = null;
	protected static Actions actions = null;
	protected static JavascriptExecutor jsDriver = null;

//	EXTENT REPORT
	protected static ExtentReports extent;
	protected static ExtentTest test, precondition;
	
	
	public static void initialiseDriver() {
		String browserName = "chrome";

		if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {				
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
//		Actions class is an ability provided by Selenium for handling keyboard and mouse events.
		actions = new Actions(driver);
//		JavaScriptExecutor is an interface that provides a mechanism to execute Javascript through selenium driver.
		jsDriver = (JavascriptExecutor) driver;
	}
	
	
	/**
	 * Captures screenshot of the current window of the browser driver
	 * 
	 * @param screenShotName
	 * @return
	 */
	protected static String capture(String screenShotName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = "testReports/screenshots/" + screenShotName + System.currentTimeMillis() + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error occurred saving screenshot!!");
			e.printStackTrace();
		}
//		return screenshotPath;						
/* change return statement to below statement if you are not using email report	*/	
		return new File (screenshotPath).getAbsolutePath();
	}
	
	
}
