package com.aditpinheiro.happytrip.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aditpinheiro.happytrip.pages.LoginPage;
import com.aditpinheiro.happytrip.pages.ScheduleFlightPage;
import com.aditpinheiro.happytrip.resources.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC_006 extends BaseClass {

	@SuppressWarnings("resource")
	@Test 
	public void login() throws IOException
	{
		//creates a toggle for test and adds all log events under it
		ExtentTest logger6 = extent.createTest("TC_006-Report");
				
		File file = new File("./src/test/resources/TestFile.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(inputStream); 
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row	= sheet.getRow(6);	
		String username = row.getCell(1).getStringCellValue();
		String password = row.getCell(2).getStringCellValue();
		String dept_time = sheet.getRow(6).getCell(3).getStringCellValue();
		
		LoginPage login = new LoginPage(driver);
		ScheduleFlightPage admin = new ScheduleFlightPage(driver);
		
		login.sendUsername(username);
		login.sendPassword(password);
		login.SignIn();
		
		//Logging into report depending on login status
		logger6.log(Status.INFO, "Beginning TC_006");
		if(login.checkLogin()) {
			logger6.log(Status.PASS, "Successfull Login");
			
			//Performing main test
			admin.ScheduleFlightClick();
			admin.ChooseDeptTime(dept_time);
			
			//Assertions
			String expected = row.getCell(3).getStringCellValue();
			String actual = admin.getDeptTime();
			
			Assert.assertEquals(actual, expected);
			if(expected.equalsIgnoreCase(actual))
				logger6.log(Status.PASS, "PASS: Flight Departure Time successfully selected");
			else
				logger6.log(Status.FAIL, "FAIL: Flight Departure Time not successfully selected");
			
			System.out.println("Actual:"+actual);
			System.out.println("Expected:"+expected);
			
		}else {
			logger6.log(Status.FAIL, "Login failed");
		}
		
	}
		
}
