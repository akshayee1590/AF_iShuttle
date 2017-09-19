package com.ishuttle.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;


public class UserReportPage extends LoadableComponent<UserReportPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(id = "user_id")    
	WebElement lnkUser;
	
		
	@FindBy(id = "form-button-save")
	WebElement btnProceed;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Branch page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public UserReportPage(WebDriver driver,ExtentTest test) {
		    this.test1=test;
	        this.driver = driver;
	        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	                iShuttleUtils.iShuttleMinElementWait);
	        PageFactory.initElements(finder, this);
	    }


		@Override
		protected void isLoaded() throws Error {
				}
		
		@Override
		protected void load() {
	        
		}
		
		/**
		 * To Select a user 
		 * 
		 */
		public void selectUser(String user) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('user_id').style.display='block';");
			Select select = new Select(lnkUser);
			select.selectByVisibleText(user);
		}
		
		/**
		 * To Click Proceed button
		 * 
		 */
		public void clicksOnProceed() {
			this.test1.log(Status.INFO,"Clicked on Save and Go back");
			iShuttleUtils.waitForElement(driver, btnProceed, 10,this.test1);
			btnProceed.click();
			}
		/**
		 * To Generate a 
		 * @param user
	     		 */
		public void createUser(String user) 	
		{
			this.test1.log(Status.INFO,"Generating  for user" +user);
			selectUser(user);
			clicksOnProceed();
			this.test1.log(Status.INFO,"Entered values in all field displayed on  page");
			this.test1.log(Status.PASS,user+" s details stored successfully");
		}


		public boolean isPageLoaded() {
			return isPageLoaded;
		}


		public void setPageLoaded(boolean isPageLoaded) {
			this.isPageLoaded = isPageLoaded;
		}
}
