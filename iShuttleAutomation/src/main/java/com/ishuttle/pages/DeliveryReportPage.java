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


public class DeliveryReportPage extends LoadableComponent<DeliveryReportPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(id = "asset_id")
	WebElement lnkAsset;
	
	@FindBy(id = "channel_id")
	WebElement lnkChannel;
		
	@FindBy(id = "form-button-save")
	WebElement btnProceed;
	
	@FindBy(css = "cancel-button")
	WebElement cancelbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Branch page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public DeliveryReportPage(WebDriver driver,ExtentTest test) {
		    this.test1=test;
	        this.driver = driver;
	        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	                iShuttleUtils.iShuttleMinElementWait);
	        PageFactory.initElements(finder, this);
	    }


		@Override
		protected void isLoaded() throws Error {
			// TODO Auto-generated method stub
			 
		}
		
		@Override
		protected void load() {
	        setPageLoaded(true);
			
		}
		
		/**
		 * To Select a Client 
		 * 
		 */
		public void selectAsset(String Asset) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('asset_id').style.display='block';");
			Select select = new Select(lnkAsset);
			select.selectByVisibleText(Asset);
		}
		/**
		 * To Enter Brand Name
		 * 
		 */
		public void selectchannel(String channel) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('channel_id').style.display='block';");
			Select select = new Select(lnkChannel);
			select.selectByVisibleText(channel);
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
		* To Generate a Delivery report
		* @param Asset
	    * @param channel
	    */
		public void generateDeliveryReport(String Asset,String channel) 	
		{
			this.test1.log(Status.INFO,"Generating Delivery report for Asset" +Asset);
			selectAsset(Asset);
			selectchannel(channel);
			clicksOnProceed();
			this.test1.log(Status.INFO,"Entered values in all field displayed on Delivery Report page");
			this.test1.log(Status.PASS,Asset+" Delivery Reports details shown successfully");
		}


		public boolean isPageLoaded() {
			return isPageLoaded;
		}


		public void setPageLoaded(boolean isPageLoaded) {
			this.isPageLoaded = isPageLoaded;
		}
}
