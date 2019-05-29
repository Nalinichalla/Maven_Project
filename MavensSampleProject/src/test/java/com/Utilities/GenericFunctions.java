package com.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

public class GenericFunctions extends StaticVariables {
	// Constructor: Create a method with the sameclass name...
	// inside a class if n number of method are there,
	// while execution system will give first priority to CONSTRUCTOR
	public GenericFunctions() {
		// check the screenshot folder is avaialble or not, if not create a folder
		ProjectDir = System.getProperty("user.dir");
		System.out.println(ProjectDir);
		File asdf = new File(ProjectDir + "\\screenshots");
		if (asdf.exists()) {
			System.out.println("screenshot folder already avaliable");
		} else {
			asdf.mkdir();
			System.out.println("successfully screenshot folder created");

		}

	}

	// launch Chrome browser
	public void chromeBrowserLaunch() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();// driver has contains some value (it is not null)
	}

	public void firefoxbrowserLaunch() {
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public void IEbrowserLaunch() {
		System.setProperty("webdriver.ie.driver", ".\\BrowserDrivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();

	}

	public void browserLaunch(String browsername) {

		if (browsername.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browsername.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", ".\\BrowserDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		} else {
			System.out.println("Please enter valid browser name");
		}
	}

	public void takescreenshot(String name) throws IOException {
		Date d = new Date();
		System.out.println(d);// Mon Apr 08 21:39:40 CDT 2019

		// create our own format dd_MM_yyyy_HH_mm_ss
		DateFormat df = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");// 08_Apr_2019_21_44_19
		String timeStamp = df.format(d);
		System.out.println(timeStamp);

		String screenshotpath = ".\\Screenshots\\";
		File asdf = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(asdf, new File(screenshotpath + name + timeStamp + ".png"));
	}

	// SendKeys By using Name loacter
	public void sendKeysByName(String locater, String inputData) {
		// Eidtbox is displayed or not then it is enable?
		WebElement element = driver.findElement(By.name(locater));

		if (element.isDisplayed() && element.isEnabled()) {
			// if any existing data has displayed in editbox, we need top clear the data
			element.clear();
			element.sendKeys(inputData);
			System.out.println("Data has entered successfully....");
		} else {
			System.out.println("Element is not displayed or not enable or locator missing in page....Please check");
		}

	}

	// click by id locator
	public void clickByID(String locater) {
		WebElement element = driver.findElement(By.id(locater));
		if (element.isDisplayed() && element.isEnabled()) {
			element.click();
			System.out.println("clicked successfully....");
		} else {
			System.out.println("Element is not displayed or not enable or locator missing in page....Please check");
		}

	}

	// Take screenshot pass or fail
	public void takeScreenshotPASSorFAIL(ITestResult res) throws Exception {
		// get the current calssname and method name
		className = res.getTestClass().getName().trim();
		methodName = res.getName().trim();
		Date d = new Date();
		System.out.println(d);// Mon Apr 08 21:39:40 CDT 2019
		// create our own format dd_MM_yyyy_HH_mm_ss
		DateFormat df = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");// 08_Apr_2019_21_44_19
		String timeStamp = df.format(d);
		System.out.println(timeStamp);

		if (res.getStatus() == ITestResult.FAILURE) {
			String screenshotpath = ".\\Screenshots\\";
			File asdf = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(asdf, new File(screenshotpath + "Fail_"+ className+"_"+methodName+timeStamp+ ".png"));
		}
		if (res.getStatus() != ITestResult.FAILURE) {
			String screenshotpath = ".\\Screenshots\\";
			File asdf = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(asdf, new File(screenshotpath + "Pass_"+ className+"_"+methodName+timeStamp+ ".png"));
		}

	}

}// class close
