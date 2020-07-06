package com.aditpinheiro.happytrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class ScheduleFlightPage {

WebDriver driver;
	
	//Initialize the driver
	public ScheduleFlightPage(WebDriver driver) {
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,30);
		PageFactory.initElements(factory,this);
	}
	
	@FindBy(how=How.XPATH, using="//*[@id=\"MainTabs\"]/li[5]/a")
	WebElement scheduleFlight;
	
	@FindBy(how=How.ID, using="flight")
	WebElement flight;
	
	@FindBy(how=How.ID, using="route")
	WebElement route;
	
	@FindBy(how=How.ID, using="distance")
	WebElement distance;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"AddSchedule\"]/dl/dd[6]/img")
	WebElement dept_calendar;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a") //9th
	WebElement dept_date; 

	
	@FindBy(how=How.XPATH,using="//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[6]/a") //9th
	WebElement dept_date_early; 
	
	@FindBy(how=How.ID, using="departureTime")
	WebElement dep_time;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"AddSchedule\"]/dl/dd[8]/img")
	WebElement arr_calendar;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[6]/a") //10th
	WebElement arr_date;
	
	@FindBy(how=How.ID, using="arrivalTime")
	WebElement arr_time;
	
	@FindBy(how=How.ID, using="classEconomy")
	WebElement economyClass;
	
	@FindBy(how=How.ID, using="signInButton")
	WebElement add;
	
	@FindBy(how=How.ID, using="classEconomy")
	WebElement classEconomy;	
	
	public void ScheduleFlightClick()
	{
		scheduleFlight.click();
	}
	
	public void ChooseFlight(String flightno)
	{
		Select chooseFlight =new Select(flight);
		chooseFlight.selectByVisibleText(flightno);
	}
	
	public String getFlight()
	{
		Select chooseFlight =new Select(flight);
		return (chooseFlight.getFirstSelectedOption().getText());
	}
	
	public void ChooseRoute(String Route)
	{
		Select chooseRoute = new Select(route);
		chooseRoute.selectByVisibleText(Route);
	}
	
	public String getRoute()
	{
		Select chooseRoute = new Select(route);
		return (chooseRoute.getFirstSelectedOption().getText());
	}
	
	public void sendDistance(String dist)
	{
		distance.click();
		distance.clear();
		distance.sendKeys(dist);
	}
	
	public String getDistance()
	{
		return distance.getAttribute("value").toString();
	}
	
	public void selectDeptDate()
	{
		dept_calendar.click();
		dept_date.click();
	}
	
	public void selectDeptDate_early()
	{
		dept_calendar.click();
		dept_date_early.click();
	}
	
	public void ChooseDeptTime(String time)
	{
		Select ChooseDeptTime = new Select(dep_time);
		ChooseDeptTime.selectByVisibleText(time);
	}
	
	public String getDeptTime() 
	{
		Select ChooseDeptTime = new Select(dep_time);
		return (ChooseDeptTime.getFirstSelectedOption().getText());		
	}
	
	public void selectArrDate()
	{
		arr_calendar.click();
		arr_date.click();
	}
	
	public void ChooseArrTime(String time)
	{
		Select ChooseArrTime = new Select(arr_time);
		ChooseArrTime.selectByVisibleText(time);
	}
	
	public String getArrTime() 
	{
		Select ChooseArrTime = new Select(arr_time);
		return (ChooseArrTime.getFirstSelectedOption().getText());		
	}
	
	public void sendEconomyPrice(String price)
	{
		classEconomy.click();
		classEconomy.clear();
		classEconomy.sendKeys(price);
	}
	
	public void addSchedule()
	{
		add.click();
	}
	
	public boolean checkAdd()
	{
		try{
			return(driver.findElement(By.id("distance_required")).isDisplayed());
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean checkAdd_Final() 
	{
		try{
			return(driver.findElement(By.id("errors1")).isDisplayed());
		}catch(Exception e){
			return false;
		}
	}
	
}
