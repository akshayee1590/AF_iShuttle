package com.ishuttle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class LoginPage extends LoadableComponent<LoginPage>{
	
	private boolean isPageLoaded;
	private final String LoginUrl;


	@FindBy(name = "identity")
	WebElement email;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(css = "button")
	WebElement btnLogin;
	
	private WebDriver driver;
	private ExtentTest test1;

	/**
	 * 
	 * Constructor class for Login Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver, String url,ExtentTest test) {

		this.test1=test;
		this.driver = driver;
		LoginUrl = url;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		driver.get(LoginUrl);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !driver.getCurrentUrl().toLowerCase().contains("/auth/login")) {
			
		}
		
	}
	/**
	 * To Enter email id on Login page to login as a user
	 * 
	 * @param username
	 */
	public void enterUserName(String username) {
		email.clear();
		email.sendKeys(username);
		this.test1.log(Status.PASS, "Enter UserName "+username);
	}
	
	/**
	 * To Enter password on Login page to login as a user
	 * 
	 * @param password
	 */
	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
		this.test1.log(Status.PASS, "Enter Password "+pwd );

		

	}

	/**
	 * To click Login button on Login page
	 */
	public void clickOnLoginButton() {
		btnLogin.click();
		this.test1.log(Status.PASS, "Click on Login Button");

		
	}
	
	public DashboardPage login(String username,String pwd,ExtentTest test)
	{
		enterUserName(username);
		enterPassword(pwd);
		clickOnLoginButton();
        return new DashboardPage(driver,test).get();


		
	}
	
	
}
