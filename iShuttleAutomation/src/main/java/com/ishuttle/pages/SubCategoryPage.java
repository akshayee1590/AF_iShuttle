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

public class SubCategoryPage extends LoadableComponent<SubCategoryPage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.export")
	WebElement btnExport;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;
	
	@FindBy(css = "table#flex1>tbody>tr>td:nth-child(2)")
	WebElement Col1Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Sub Category page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public SubCategoryPage(WebDriver driver,ExtentTest test) {

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
		                    .withMessage("Category Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/subcategory"));
		    		iShuttleUtils.waitForElement(driver, btnExport, 10,this.test1);
		    		this.test1.log(Status.PASS,"Category page is displayed");
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Sub Category Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/subcategory"));
		        }
		}
		
	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To search category
	 * @return 
	 * 
	 */
	public void searchSubCategory(String searchText) {
		this.test1.log(Status.INFO,"Searching a Sub Category by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		this.test1.log(Status.PASS,"Searched a Sub Category by Search text :- " +searchText);
	}
	
	/**
	 * To search category
	 * @return 
	 * 
	 */
	public boolean verifySearchSubCategory(String searchText) {
		boolean flag = false;
		this.test1.log(Status.INFO,"verifying search results");
		try
		{
		if(Col1Row1.getText().equals(searchText))
		{
			flag=true;
		}
		
		}catch(NoSuchElementException e)
		{
			flag=false;
		}
		return flag;
	}
	

}
