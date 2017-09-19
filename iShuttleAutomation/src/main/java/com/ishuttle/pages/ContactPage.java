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

public class ContactPage extends LoadableComponent<ContactPage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(name = "client_id")
	WebElement clientname;
	
	@FindBy(name = "branch_id")
	WebElement branchname;
	
	@FindBy(id = "field-contact_name")
	WebElement contactname;
	
	@FindBy(id = "field-contact_phone")
	WebElement contactphone;
	
	@FindBy(id = "field-contact_email")
	WebElement contactemail;
	
	@FindBy(id = "field-contact_mobile")
	WebElement contactmobile;
	
	@FindBy(id = "field-remark")
	WebElement remark;
	
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;

	@FindBy(css = "table#flex1>tbody>tr>td:nth-child(4)")
	WebElement Col4Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Contact Page Here we initializing the driver for page factory 
	 * objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public ContactPage(WebDriver driver,ExtentTest test) {

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
		                    .withMessage("Contact Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/contact"));
		            this.test1.log(Status.PASS,"Contact is displayed");
		    		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Contact Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/contact"));
		        }
		}
		

		
	

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add Contact page
	 * 
	 */
	public void navAddContact() {
		 this.test1.log(Status.INFO,"Clicked on Add Contact button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		this.test1.log(Status.PASS,"Navigated to Add Contact page");
	}
	
	/**
	 * To Select a Client name
	 * 
	 */
	public void selectClientname(String client_name) {
		 this.test1.log(Status.INFO,"Select Client Name");
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-client_id').style.display='block';");
		Select select = new Select(clientname);
		select.selectByVisibleText(client_name);
	}
	
	/**
	 * To Select a Branch name
	 * 
	 */
	public void selectBranchname(String branch_name) {
		 this.test1.log(Status.INFO,"Select Branch Name");
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("var x=document.getElementsByName('branch_id'); x[0].style.display='block';");
		Select select = new Select(branchname);
		select.selectByVisibleText(branch_name);
	}
	
	/**
	 * To Enter Contact Name
	 * 
	 */
	public void enterContactName(String contact_name)
	{
		 this.test1.log(Status.INFO,"Enter contact name");
		contactname.clear();
		contactname.sendKeys(contact_name);
	}
	
	/**
	 * To Enter Phone
	 * 
	 */
	public void enterContactPhone(String contact_phone)
	{
		 this.test1.log(Status.INFO,"Enter contact phone");
		contactphone.clear();
		contactphone.sendKeys(contact_phone);
	}
	
	/**
	 * To Enter Email
	 * 
	 */
	public void enterContactEmail(String contact_email)
	{
		 this.test1.log(Status.INFO,"Enter contact email");
		contactemail.clear();
		contactemail.sendKeys(contact_email);
	}
	
	/**
	 * To Enter Mobile
	 * 
	 */
	public void enterContactMobile(String contact_mobile)
	{
		 this.test1.log(Status.INFO,"Enter contact mobile");
		contactmobile.clear();
		contactmobile.sendKeys(contact_mobile);
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
	 * To create a Contact
	 * @param client_name
     * @param branch_name
     * @param branch_address
     * @param branch_city
     * @param branch_email
     * @param branch_phone
	 * @param remarkText
	 */
	public void createContact(String client_name,String branch_name,String contact_name,
			String contact_phone,String contact_email,String contact_mobile,String remarkText) {
		 this.test1.log(Status.INFO,"Creating a Branch" +branch_name);
		selectClientname(client_name);
		selectBranchname(branch_name);
		enterContactName(contact_name);
		enterContactPhone(contact_phone);
		enterContactEmail(contact_email);
		enterContactMobile(contact_mobile);
		enterRemark(remarkText);
		clicksaveandgoback();
		this.test1.log(Status.PASS,"Entered values in all field displayed on Add Branch page");
		this.test1.log(Status.PASS,branch_name+" Branch details stored successfully");
	}
	
	/**
	 * To Edit a Contact
     * @param branch_address
     * @param branch_city
     * @param branch_email
     * @param branch_phone
	 * @param remarkText
	 */
	public void editContact(String contact_name,
			String contact_phone,String contact_email,String contact_mobile,String remarkText) {
		 this.test1.log(Status.INFO,"Edit a Contact" +contact_name);
		enterContactName(contact_name);
		enterContactPhone(contact_phone);
		enterContactEmail(contact_email);
		enterContactMobile(contact_mobile);
		enterRemark(remarkText);
		clicksaveandgoback();
		this.test1.log(Status.PASS,contact_name+" Contact details stored successfully");
	}
	
	/**
	 * To navigate to Edit Contact page
	 * 
	 */
	public void navEditContact() {
		 this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		this.test1.log(Status.PASS,"Navigated to Edit Contact page");
	}
	
	/**
	 * To navigate to Delete Contact page
	 * 
	 */
	public void deleteContact(boolean flag) {
		 this.test1.log(Status.INFO,"Click on Delete link");
		iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
		lnkDelete.click();
		if(flag==true)
		{
			okbtn.click();
			this.test1.log(Status.PASS,"Deleted Contact");

		}else
		{
			
		}
		
	}
	
	
	/**
	 * To search Contact
	 * @return 
	 * 
	 */
	public void searchContact(String searchText) {
		iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
		 this.test1.log(Status.INFO,"Searching a Contact by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		this.test1.log(Status.PASS,"Searched a Contact by Search text :- " +searchText);

	}

	/**
	 * To search Brand
	 * @return 
	 * 
	 */
	public boolean verifySearchContact(String searchText) {
		boolean flag = false;
		iShuttleUtils.waitForElement(driver, Col4Row1, 10,this.test1);

		 this.test1.log(Status.INFO,"verifying search results");
		try
		{
		if(Col4Row1.getText().equals(searchText))
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
