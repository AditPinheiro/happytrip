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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aditpinheiro.happytrip.pages.LoginPage;
import com.aditpinheiro.happytrip.pages.ScheduleFlightPage;
import com.aditpinheiro.happytrip.resources.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC_010 extends BaseClass{

	@SuppressWarnings("resource")
	@Test ()
	public void login() throws IOException
	{
		//creates a toggle for test and adds all log events under it
		ExtentTest logger10 = extent.createTest("TC_010-Report");
		
				
		File file = new File("./src/test/resources/TestFile.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(inputStream); 
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row	= sheet.getRow(10);	
		String username = row.getCell(1).getStringCellValue();
		String password = row.getCell(2).getStringCellValue();
		
		String price = row.getCell(3).toString();	 
		String flightno = sheet.getRow(1).getCell(3).getStringCellValue();
		String route = sheet.getRow(2).getCell(3).getStringCellValue();
		String dist = sheet.getRow(3).getCell(3).toString();
		String dept_time = sheet.getRow(6).getCell(4).toString(); //fill in the correct time (close to time of entry)
		String arr_time = sheet.getRow(8).getCell(3).getStringCellValue();
		
		LoginPage login = new LoginPage(driver);
		ScheduleFlightPage admin = new ScheduleFlightPage(driver);
		
		login.sendUsername(username);
		login.sendPassword(password);
		login.SignIn();
		
		//Logging into report depending on login status
		logger10.log(Status.INFO, "Beginning TC_010");
		if(login.checkLogin()) {
			logger10.log(Status.PASS, "Successfull Login");

			//Performing main test
			admin.ScheduleFlightClick();			
			admin.ChooseFlight(flightno);
			admin.ChooseRoute(route);
			admin.sendDistance(dist);
			admin.selectDeptDate_early();
			admin.ChooseDeptTime(dept_time);
			admin.selectArrDate();
			admin.ChooseArrTime(arr_time);
			
			admin.sendEconomyPrice(price);
			admin.addSchedule();
			
			if(admin.checkAdd_Final())
			{
				logger10.log(Status.PASS, "PASS: Error Message displayed because departure time was less than 3 hours fom now");
			}
			else 
			{
				logger10.log(Status.FAIL, "FAIL: Error Message not displayed eventhough less than 3hrs");
			}
			
		}else {
			logger10.log(Status.FAIL, "Login failed");
		}
		
		
		
		boolean isTimeError = driver.findElement(By.id("errors1")).isDisplayed();
		System.out.println("Error messaged displayed successfully?: "+isTimeError);
	}

}
