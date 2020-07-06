package com.aditpinheiro.happytrip.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass{
	
protected static WebDriver driver; //static so that child classes don't need objects to use driver
static ExtentHtmlReporter report;
protected static ExtentReports extent;

	@BeforeSuite
	public static void extentReport() {
		//starts reporters
		report = new ExtentHtmlReporter("./reports/happytripTestReport.html");
		//creates extent reports
		extent = new ExtentReports();
		//attaches report to the reporter
		extent.attachReporter(report);
		
		report.config().setDocumentTitle("Reports");
		report.config().setReportName("Happy Trip Extent Report");
		
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("Browser", "Google Chrome");
		extent.setSystemInfo("Application URL", "http://43.254.161.195:8085/happytripcrclean1/");
	}
	
	@BeforeMethod 	//can also remain @BeforeTest
	public static void setup()
	{		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://43.254.161.195:8085/happytripcrclean1/");
		driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]/a")).click();
		
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	@AfterMethod
	public static void teardown()
	{
		driver.close();
		driver.quit();
	}
	
	@AfterSuite
	public static void extentReportdone() {
		extent.flush();
	}

}
