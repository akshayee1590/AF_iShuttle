package com.ishuttle.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;

public class AssetPage extends LoadableComponent<AssetPage> {

	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;

	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(id = "field-asset_id")
	WebElement asset;
	
	@FindBy(id = "field-channels")
	WebElement channelname;
	
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;
	
	@FindBy(id = "field-remark")
	WebElement remark;
	
	@FindBy(linkText = "Asset")
	WebElement lnkAsset;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	 public AssetPage(WebDriver driver,ExtentTest test) {
		 
		    this.test1=test;
	        this.driver = driver;
	        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	                iShuttleUtils.iShuttleMinElementWait);
	        PageFactory.initElements(finder, this);
	    }


		@Override
		protected void isLoaded() throws Error {
			// TODO Auto-generated method stub
			 if (!isPageLoaded) {
		            Assert.fail();
		        }
		       
		        try {
		            (new WebDriverWait(driver, 20).pollingEvery(200,
		                    TimeUnit.MILLISECONDS).ignoring(
		                    NoSuchElementException.class)
		                    .withMessage("Asset Page did not open up."))
		                    .until(ExpectedConditions.urlContains("mrcontrol"));
		            this.test1.log(Status.PASS,"Asset Page is displayed");
		    		iShuttleUtils.waitForElement(driver, btnAdd,10,this.test1);
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Asset page did not open up."))
		                     .until(ExpectedConditions.urlContains("Mrcontrol/viewasset/"));
		        }
		}

		@Override
		protected void load() {
	        isPageLoaded = true;
			
		}

		
		
		
		/**
		 * To navigate to Add Asset page
		 * 
		 */
		public void navAddAsset() {
			 this.test1.log(Status.INFO,"Clicked on Add Asset button");
			iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
			btnAdd.click();
			 this.test1.log(Status.PASS,"Navigated to Add Asset page");
		}
		
		 /** To Select a Asset 
		 * 
		 */
		
		public void selectAssetname(String Asset) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('field-asset_id').style.display='block';");
			Select select = new Select(asset);
			select.selectByVisibleText(Asset);
		}
		
		 /** To Select a Channel 
		 * 
		 */
		
		public void selectChannelName(String Channel) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('field-channels').style.display='block';");
			Select select = new Select(channelname);
			select.selectByVisibleText(Channel);
		}
		
		/**
		 * To enter Remark
		 * 
		 * @param text
		 */
		public void enterRemark(String text) {
			 this.test1.log(Status.INFO,"Enter Remark");
			remark.clear();
			remark.sendKeys(text);
		}

		/**
		 * To Click Save and Go back to list button
		 * 
		 */
		public void clicksaveandgoback() {
			 this.test1.log(Status.INFO,"Clicked on Save and Go back");
			iShuttleUtils.waitForElement(driver, btnsaveandgoback, 10,this.test1);
			btnsaveandgoback.click();
			iShuttleUtils.waitForElement(driver, btnsaveandgoback, 10,this.test1);
		}
		
		/**
		 * To Add a asset
		 * @param Asset
	     * @param Channel
	     * @param remarkText
		 */
		public void AddAsset(String Asset,String Channel,String remarkText) {
			 this.test1.log(Status.INFO,"Adding Asset" +Asset);
			selectAssetname(Asset);
			selectChannelName(Channel);
			enterRemark(remarkText);
			clicksaveandgoback();
			this.test1.log(Status.PASS,"Entered values in all field displayed on asset page");
			this.test1.log(Status.PASS,Asset+" asset details stored successfully");
		}

		/**
		 * To search Asset
		 * @return 
		 * 
		 */
		public void searchAsset(String searchText) {
			iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
			 this.test1.log(Status.INFO,"Searching a Contact by Search text :- " +searchText);
			searchBox.clear();
			searchBox.sendKeys(searchText);
			this.test1.log(Status.PASS,"Searched a Contact by Search text :- " +searchText);

		}





}