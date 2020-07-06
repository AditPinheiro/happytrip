package com.aditpinheiro.happytrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

	WebDriver driver;
	
	//Initialize the driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,5);
		PageFactory.initElements(factory,this);
	}
	
	//Using FindBy for locating elements
	@FindBy(how=How.ID, using="username")
	WebElement userName;
	
	@FindBy(how=How.ID, using="password")
	WebElement password;
	
	@FindBy(how=How.ID, using="signInButton")
	WebElement signIn;
	
	public void sendUsername(String UserName)
	{
		userName.click();
		userName.clear();
		userName.sendKeys(UserName);
	}
	public void sendPassword(String Password)
	{
		password.click();
		password.clear();
		password.sendKeys(Password);
	}
	
	public void SignIn()
	{
		signIn.click();
	}
	
	public boolean checkLogin()
	{
		try{
			return(driver.findElement(By.linkText("Sign Out")).isDisplayed());
		}catch(Exception e){
			return false;
		}			
	}
}
