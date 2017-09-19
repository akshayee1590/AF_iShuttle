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

public class ChannelHousePage extends LoadableComponent<ChannelHousePage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;

	@FindBy(id = "field-channelhouse_name")
	WebElement channelhousename;
	
	@FindBy(name = "channelhouse_type")
	WebElement channelhouseType;
	
	@FindBy(id = "field-channelhouse_ip")
	WebElement channelhouseip;
	
	@FindBy(id = "field-channelhouse_location")
	WebElement channelhouselocation;
	
	@FindBy(name = "houseprocess_id")
	WebElement houseprocess;
	
	@FindBy(id = "field-ftp_link")
	WebElement ftplink;
	
	@FindBy(id = "field-ftp_user")
	WebElement ftpuser;
	
	@FindBy(id = "field-ftp_password")
	WebElement ftppassword;
	
	@FindBy(id = "field-remark")
	WebElement remark;
			
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
	 * Constructor class for Channel House Here we initializing the driver for page 
	 * factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public ChannelHousePage(WebDriver driver,ExtentTest test) {
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
		                    .withMessage("Channel House Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/channelhouse"));
		    		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		             this.test1.log(Status.PASS,"Channel House Page is displayed");
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Channel House Master page did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/channelhouse"));
		        }
		}
		

	

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add Channel House page
	 * 
	 */
	public void navAdd() {
		 this.test1.log(Status.INFO,"Clicked on Add Channel House button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		 this.test1.log(Status.PASS,"Navigated to Add Channel House page");
	}

	
	/**
	 * To enter Channel House
	 * 
	 * @param channelhouse
	 */
	public void enterChannelHouse(String channelhouse) {
		 this.test1.log(Status.INFO,"Enter Channel House");
		channelhousename.clear();
		channelhousename.sendKeys(channelhouse);
	}
	
	/**
	 * To select a Channel House Type
	 * @param channelhouse_type
	 */
	public void selectChannelHouseType(String channelhouse_type) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-channelhouse_type').style.display='block';");
		Select select = new Select(channelhouseType);
		select.selectByVisibleText(channelhouse_type);
	
	}
	

	/**
	 * To enter Channel House Ip
	 * 
	 * @param channelhouseIp
	 */
	public void enterChannelHouseIP(String channelhouseIp) {
		 this.test1.log(Status.INFO,"Enter Channel House IP");
		channelhouseip.clear();
		channelhouseip.sendKeys(channelhouseIp);
	}
	
	/**
	 * To enter Channel House Location
	 * 
	 * @param channelhouseLocation
	 */
	public void enterChannelHouseLocation(String channelhouseLocation) {
		 this.test1.log(Status.INFO,"Enter Channel House IP");
		channelhouselocation.clear();
		channelhouselocation.sendKeys(channelhouseLocation);
	}
	
	/**
	 * To select a House Process
	 * @param house_process
	 */
	/*/public void selectHouseProcess(String house_process) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-houseprocess_id').style.display='block';");
		Select select = new Select(houseprocess);
		select.selectByVisibleText(house_process);
	}/*/
	
	/**
	 * To enter FTP Link
	 * 
	 * @param ftpLink
	 */
	public void enterFTPLink(String ftpLink) {
		 this.test1.log(Status.INFO,"Enter Channel House IP");
		ftplink.clear();
		ftplink.sendKeys(ftpLink);
	}
	
	/**
	 * To enter FTP Username
	 * 
	 * @param ftpLink
	 */
	public void enterFTPUserName(String ftp_username) {
		 this.test1.log(Status.INFO,"Enter FTP User Name");
		ftpuser.clear();
		ftpuser.sendKeys(ftp_username);
	}
	
	/**
	 * To enter FTP Password
	 * 
	 * @param ftpLink
	 */
	public void enterFTPPassword(String ftp_password) {
		 this.test1.log(Status.INFO,"Enter FTP Password");
		ftppassword.clear();
		ftppassword.sendKeys(ftp_password);
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
	
	/** To create Channel House
	 * @param channelhouse
	 * @param channelhouse_type
	 * @param channelhouseIp
	 * @param channelhouseLocation
	 * @param house_process
	 * @param ftpLink
	 * @param ftp_username
	 * @param ftp_password
	 * @param remark
	 */
	public void createChannelHouse(String channelhouse,String channelhouse_type,String channelhouseIp,
			String channelhouseLocation,
			String house_process,String ftpLink,String ftp_username,String ftp_password,String remark)
	{
		 this.test1.log(Status.INFO,"Creating a Channel House" +channelhouse);
		 this.test1.log(Status.PASS,"Enter values in all field displayed on Add Channel House page");
		enterChannelHouse(channelhouse);
		selectChannelHouseType(channelhouse_type);
		enterChannelHouseIP(channelhouseIp);
		enterChannelHouseLocation(channelhouseLocation);
		//selectHouseProcess(house_process);
		enterFTPLink(ftpLink);
		enterFTPUserName(ftp_username);
		enterFTPPassword(ftp_password);
		enterRemark(remark);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,channelhouse+" Channel House details stored successfully");		
		
	}

	/** To Edit a Channel House
	 * @param channelhouseIp
	 * @param channelhouseLocation
	 * @param house_process
	 * @param ftpLink
	 * @param ftp_username
	 * @param ftp_password
	 * @param remark
	 */
	public void editChannelHouse(String channelhouseIp,	String channelhouseLocation,
			String ftpLink,String ftp_username,String ftp_password,String remark)
	{
		 this.test1.log(Status.INFO,"Editing a ChannelHouse");
		 this.test1.log(Status.PASS,"Modify values in the fields");
		enterChannelHouseIP(channelhouseIp);
		enterChannelHouseLocation(channelhouseLocation);
		enterFTPLink(ftpLink);
		enterFTPUserName(ftp_username);
		enterFTPPassword(ftp_password);
		enterRemark(remark);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,"Channel House details modified successfully");
	}
	
	/**
	 * To navigate to Edit Channel House page
	 * 
	 */
	public void navEdit() {
		 this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		 this.test1.log(Status.PASS,"Navigated to Edit Channel House page");
	}
	
	/**
	 * To navigate to Delete Channel House page
	 * 
	 */
	public void delete(boolean flag) {
		 this.test1.log(Status.INFO,"Click on Delete link");
		iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
		lnkDelete.click();
		if(flag==true)
		{
			okbtn.click();
			 this.test1.log(Status.PASS,"Deleted Channel House");

		}else
		{
			
		}
		
	}
	
	/**
	 * To search Channel House
	 * @return 
	 * 
	 */
	public void searchChannelHouse(String searchText) {
		 this.test1.log(Status.INFO,"Searching a Channel House by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		 this.test1.log(Status.PASS,"Searched a Channel House by Search text :- " +searchText);

	}
	
	/**
	 * To search Channel House
	 * @return 
	 * 
	 */
	public boolean verifySearchChannelHouse(String searchText) {
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
