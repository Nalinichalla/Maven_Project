package com.TestScenarios;

import org.testng.annotations.Test;

import com.ObjectRepository.Locators;
import com.Utilities.GenericFunctions;
import com.Utilities.StaticVariables;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class FB_Login extends StaticVariables {
	
	//create reference of locaters class
	Locators obj = new Locators();
	GenericFunctions gfn = new GenericFunctions();
	
	
	@Test
	public void login() throws InterruptedException, Exception {
		FileInputStream propertyFilPpathLocation = new FileInputStream(".\\TestData\\Input.properties");
		//create reference of property class
		Properties p = new Properties();
		p.load(propertyFilPpathLocation);
		
		driver.get(p.getProperty("URL"));
		
		gfn.sendKeysByName(obj.FB_USERNAME, p.getProperty("Email"));
		gfn.sendKeysByName(obj.FB_PASSWORD, p.getProperty("Password"));
		gfn.clickByID(obj.FB_loginbutton);
		Thread.sleep(5000);
		
	}

	@AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		//gfn.takescreenshot("test");
		//results_classname_method_timestamp.png
		gfn.takeScreenshotPASSorFAIL(res);
	}
	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String Browser) {
		//gfn.chromeBrowserLaunch();
		gfn.browserLaunch(Browser);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
