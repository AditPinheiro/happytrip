package com.aditpinheiro.happytrip.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aditpinheiro.happytrip.pages.LoginPage;
import com.aditpinheiro.happytrip.pages.ScheduleFlightPage;
import com.aditpinheiro.happytrip.resources.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC_003 extends BaseClass{

	@SuppressWarnings("resource")
	@Test 
	public void login() throws IOException
	{
		//creates a toggle for test and adds all log events under it		
		ExtentTest logger3 = extent.createTest("TC_003-Report");

		File file = new File("./src/test/resources/TestFile.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(inputStream); 
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row	= sheet.getRow(3);	
		String username = row.getCell(1).getStringCellValue();
		String password = row.getCell(2).getStringCellValue();
		String distance = row.getCell(3).toString();
		LoginPage login = new LoginPage(driver);
		ScheduleFlightPage admin = new ScheduleFlightPage(driver);

		login.sendUsername(username);
		login.sendPassword(password);
		login.SignIn();	

		//Logging into report depending on login status
		logger3.log(Status.INFO, "Beginning TC_003");

		if(login.checkLogin()) {
			logger3.log(Status.PASS, "Successfull Login");

			admin.ScheduleFlightClick();	
			admin.sendDistance(distance);

			String expected = row.getCell(3).toString();
			String actual = admin.getDistance();
			
			Assert.assertEquals(actual, expected);
			
			if(expected.equalsIgnoreCase(actual))
				logger3.log(Status.PASS, "PASS: Flight Distance successfully entered");
			else
				logger3.log(Status.PASS, "FAIL: Flight Distance not successfully entered");
			
			System.out.println("Actual:"+actual);
			System.out.println("Expected:"+expected);
			
		}else {
			logger3.log(Status.FAIL, "Login failed");
		}

	}

}
