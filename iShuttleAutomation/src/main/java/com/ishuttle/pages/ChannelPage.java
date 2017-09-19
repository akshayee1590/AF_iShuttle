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

public class ChannelPage extends LoadableComponent<ChannelPage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(id = "field-channel_name")
	WebElement channelname;
	
	@FindBy(name = "channel_type")
	WebElement channelType;
	
	@FindBy(name = "channelhouse_id")
	WebElement channelhouseId;
	
	@FindBy(name = "languages[]")
	WebElement language;
	
	@FindBy(id = "field-notify_to")
	WebElement notifyTo;


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
	WebElement Col2Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Channel Page Here we initializing the driver for page factory 
	 * objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public ChannelPage(WebDriver driver,ExtentTest test) {
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
		                    .withMessage("Channel Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/channel"));					iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
							iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
							 this.test1.log(Status.PASS,"Channel Page is displayed");
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Channel Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/channel"));
		        }
		}
		

		
	

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add Channel page
	 * 
	 */
	public void navAdd() {
		 this.test1.log(Status.INFO,"Clicked on Add Channel button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		 this.test1.log(Status.PASS,"Navigated to Add Channel page");
	}
	
	/**
	 * To enter Channel
	 * 
	 * @param text
	 */
	public void enterChannel(String channel_name) {
		 this.test1.log(Status.INFO,"Enter Remark");
		channelname.clear();
		channelname.sendKeys(channel_name);
	}
	
	/**
	 * To select a channel type
	 * @param client_type
	 */
	public void selectChannelType(String channel_type) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-channel_type').style.display='block';");
		Select select = new Select(channelType);
		select.selectByVisibleText(channel_type);	
	}
	
	/**
	 * To select a Channel House
	 * @param channel_house
	 */
	public void selectChannelHouse(String channel_house) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-channelhouse_id').style.display='block';");
		Select select = new Select(channelhouseId);
		select.selectByVisibleText(channel_house);	
	}
	
	
	/**
	 * To select a Language
	 * @param Language
	 */
	public void selectLanguages(String channel_language) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-languages').style.display='block';");
		Select select = new Select(language);
		select.selectByVisibleText(channel_language);	
	}
	/*
	 * To enter notify To
	 * 
	 * @param notifyTo2
	 */
	
	public void enternotifyTo(String notify_To) {
		this.test1.log(Status.INFO,"Enter Notify To");
		notifyTo.clear();
		notifyTo.sendKeys(notify_To);
		
	}
		

	/**
	 * 
	 * 
	 */
	/**To create a Channel
	 * @param channel_name
	 * @param channel_type
	 * @param channel_house
	 * @param channel_language
	 * @param NotifyTo
	 * @param remarkText
	 */
	public void createChannel(String channel_name,String channel_type,String channel_house,
			String channel_language,String remarkText, String notify_To) {
		
		 this.test1.log(Status.INFO,"Creating a Channel" +channel_name);
		enterChannel(channel_name);
		selectChannelType(channel_type);
		selectChannelHouse(channel_house);
		selectLanguages(channel_language);
		enternotifyTo(notify_To);
		enterRemark(remarkText);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,"Entered values in all field displayed on Add Channel page");
		 this.test1.log(Status.PASS,channel_name+" Channel details stored successfully");
	}
	
	/**To Edit a Channel
	 * @param channel_name
	 * @param channel_type
	 * @param remarkText
	 */
	public void editChannel(String channel_name,String channel_type,String remarkText) 
	{
		 this.test1.log(Status.INFO,"Editing a Channel" +channel_name);
		enterChannel(channel_name);
		selectChannelType(channel_type);
		enterRemark(remarkText);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,"Entered values in all field displayed on Add Channel page");
		 this.test1.log(Status.PASS,channel_name+" Channel details stored successfully");
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
	 * To navigate to Edit Channel page
	 * 
	 */
	public void navEditChannel() {
		 this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		 this.test1.log(Status.PASS,"Navigated to Edit Channel page");
	}
	
	/**
	 * To navigate to Delete Channel page
	 * 
	 */
	public void delete(boolean flag) {
		 this.test1.log(Status.INFO,"Click on Delete link");
		iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
		lnkDelete.click();
		if(flag==true)
		{
			okbtn.click();
			 this.test1.log(Status.PASS,"Deleted Channel");

		}else
		{
			
		}
		
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
	
	/**
	 * To search Channel
	 * @return 
	 * 
	 */
	public void searchChannel(String searchText) {
		iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
		 this.test1.log(Status.INFO,"Searching a Channel by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		 this.test1.log(Status.PASS,"Searched a Channel by Search text :- " +searchText);

	}
	
	/**
	 * To search Channel
	 * @return 
	 * 
	 */
	public boolean verifySearchChannel(String searchText) {
		boolean flag = false;
		iShuttleUtils.waitForElement(driver, Col2Row1, 10,this.test1);

		 this.test1.log(Status.INFO,"verifying search results");
		try
		{
		if(Col2Row1.getText().equals(searchText))
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
