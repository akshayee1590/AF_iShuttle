package com.ishuttle.pages;

import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;

public class CreateOrder extends LoadableComponent<CreateOrder> {

	
	private boolean isPageLoaded;

	
	@FindBy(id = "savedata")
	WebElement crateorder;
	
	@FindBy(id="chk_mr_asset")
	WebElement SelectAsset;
	
	private WebDriver driver;
	private ExtentTest test1;

	public CreateOrder(WebDriver driver,ExtentTest test) {
		 
	    this.test1=test;
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                iShuttleUtils.iShuttleMinElementWait);
        PageFactory.initElements(finder, this);
    }

	@Override
	protected void isLoaded() throws Error {
			 if (!isPageLoaded) {
	            Assert.fail();
	        }
	       
	        try {
	            (new WebDriverWait(driver, 20).pollingEvery(200,
	                    TimeUnit.MILLISECONDS).ignoring(
	                    NoSuchElementException.class)
	                    .withMessage("Create Order Page did not open up."))
	                    .until(ExpectedConditions.urlContains("mrcontrol"));
	            this.test1.log(Status.PASS,"Create Order Page is displayed");
	    		iShuttleUtils.waitForElement(driver, crateorder,10,this.test1);
	        } catch (TimeoutException e) {
	        	 driver.get(driver.getCurrentUrl());
	             (new WebDriverWait(driver, 20).pollingEvery(200,
	                     TimeUnit.MILLISECONDS).ignoring(
	                     NoSuchElementException.class)
	                     .withMessage("Create Order page did not open up."))
	                     .until(ExpectedConditions.urlContains("orders/createorder/"));
	        }
	}

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}
	
	

	public void selectAsset() {
		if (!SelectAsset.isSelected())
			SelectAsset.click();
	}
	
	
	public void Createorder() {
		 this.test1.log(Status.INFO,"Clicked on Save and Go back");
		iShuttleUtils.waitForElement(driver, crateorder, 10,this.test1);
		crateorder.click();
		iShuttleUtils.waitForElement(driver, crateorder, 10,this.test1);
	}

	/**
	 * To Add a asset
	 * @param SelectAsset
     * @param crateorder
	 */
	public void AddAsset() {
		 this.test1.log(Status.INFO,"Adding Asset" );
		 selectAsset();
		 Createorder();
		this.test1.log(Status.PASS,"Select all assets on Create Order page");
		this.test1.log(Status.PASS," Order created successfully");
	}
	
	
	
	
}
