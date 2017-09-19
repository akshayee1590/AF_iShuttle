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


public class LanguagePage extends LoadableComponent<LanguagePage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(id = "field-language_name")
	WebElement languagename;
	
	@FindBy(id = "field-remark")
	WebElement remark;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
			
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;
	
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
	 * Constructor class for Language Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public LanguagePage(WebDriver driver,ExtentTest test) {

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
		                    .withMessage("Language Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/language"));
		    		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		    		this.test1.log(Status.PASS,"Language page is displayed");
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Language Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/language"));
		        }
		}
		
	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add Language page
	 * 
	 */
	public void navAdd() {
		this.test1.log(Status.INFO,"Clicked on Add Language button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		this.test1.log(Status.PASS,"Navigated to Add Language page");
	}
	
	/**
	 * To enter Remark
	 * 
	 * @param text
	 */
	public void enterLanguageName(String text) {
		this.test1.log(Status.INFO,"Enter Language");
		languagename.clear();
		languagename.sendKeys(text);
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
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);

	}
	
	/**To create a language
	 * @param language
	 * @param remark
	 */
	public void createLanguage(String language,String remark)
	{
		this.test1.log(Status.INFO,"Creating a Language" +language);
		this.test1.log(Status.PASS,"Enter values in all field displayed on Add Language page");
		enterLanguageName(language);
		enterRemark(remark);
		clicksaveandgoback();
		this.test1.log(Status.PASS,language+" Language details stored successfully");		
		
	}
	
	/**To edit Language
	 * @param language
	 * @param remark
	 */
	public void editLanguage(String language,String remark)
	{
		this.test1.log(Status.INFO,"Editing a Language" +language);
		enterLanguageName(language);
		enterRemark(remark);
		clicksaveandgoback();
		this.test1.log(Status.PASS,language+" Language details modified successfully");			
	}
	
	/**
	 * To navigate to Edit Language page
	 * 
	 */
	public void navEdit() {
		this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		this.test1.log(Status.PASS,"Navigated to Edit Language page");
	}
	
	/**
	 * To navigate to Delete Language page
	 * 
	 */
	public void delete(boolean flag) {
		this.test1.log(Status.INFO,"Click on Delete link");
		iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
		lnkDelete.click();
		if(flag==true)
		{
			okbtn.click();
			this.test1.log(Status.PASS,"Deleted Language");

		}else
		{
			
		}
		
	}
	
	/**
	 * To search Language
	 * @return 
	 * 
	 */
	public void searchLanguage(String searchText) {
		this.test1.log(Status.INFO,"Searching a Language by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		this.test1.log(Status.PASS,"Searched a Language by Search text :- " +searchText);
	}
	
	/**
	 * To search Language
	 * @return 
	 * 
	 */
	public boolean verifySearchLanguage(String searchText) {
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
