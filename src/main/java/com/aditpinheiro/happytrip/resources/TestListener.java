package com.aditpinheiro.happytrip.resources;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;


public class TestListener extends BaseClass implements ITestListener{
	WebDriver driver;
	String filePath = ".screenshots/";
	BaseClass base = new BaseClass();
	
	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getName().toString();
		driver=base.getDriver();
		takeScreenShot(methodName,driver);
	}
	
	private void takeScreenShot(String methodName, WebDriver driver) {
		System.out.println("Screenshot function called");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			Files.copy(scrFile, new File(filePath+methodName+".png"));
			System.out.println("***Placed screenshot in "+filePath+"***");
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
