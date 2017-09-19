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


public class BranchPage extends LoadableComponent<BranchPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(name = "client_id")
	WebElement clientname;
	
	@FindBy(id = "field-branch_name")
	WebElement branchname;
	
	@FindBy(id = "field-branch_address")
	WebElement branchaddress;
	
	@FindBy(id = "field-branch_city")
	WebElement branchcity;

	@FindBy(id = "field-branch_email")
	WebElement branchemail;
	
	@FindBy(id = "field-branch_phone")
	WebElement branchphone;
	
	@FindBy(id = "field-remark")
	WebElement remark;
	
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;

	@FindBy(css = "table#flex1>tbody>tr>td:nth-child(3)")
	WebElement Col3Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Branch page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public BranchPage(WebDriver driver,ExtentTest test) {
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
		                    .withMessage("Branch Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/branch"));
		            this.test1.log(Status.PASS,"Branch Page is displayed");
					iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);

		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Branch Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/branch"));
		        }
		}
		
		@Override
		protected void load() {
	        isPageLoaded = true;
			
		}

		/**
		 * To navigate to Add Branch page
		 * 
		 */
		public void navAddBranch() {
			
			this.test1.log(Status.INFO,"Clicked on Add Branch button");
			iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
			btnAdd.click();
			this.test1.log(Status.PASS,"Navigated to Add Branch page");
		}
		
		/**
		 * To Select a Client name
		 * 
		 */
		public void selectClientname(String client_name) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('field-client_id').style.display='block';");
			Select select = new Select(clientname);
			select.selectByVisibleText(client_name);
		}
		/**
		 * To Enter Branch Name
		 * 
		 */
		public void enterBranchName(String branch_name)
		{
			this.test1.log(Status.INFO,"Enter branch name");
			branchname.clear();
			branchname.sendKeys(branch_name);
		}
		
		/**
		 * To Enter Address
		 * 
		 */
		public void enterBranchAddress(String branch_address)
		{
			this.test1.log(Status.INFO,"Enter branch address");
			branchaddress.clear();
			branchaddress.sendKeys(branch_address);
		}
		
		/**
		 * To Enter City
		 * 
		 */
		public void enterBranchCity(String branch_city)
		{
			this.test1.log(Status.INFO,"Enter branch city");
			branchcity.clear();
			branchcity.sendKeys(branch_city);
		}
		
		/**
		 * To Enter Email
		 * 
		 */
		public void enterBranchEmail(String branch_email)
		{
			this.test1.log(Status.INFO,"Enter branch email");
			branchemail.clear();
			branchemail.sendKeys(branch_email);
		}
		
		/**
		 * To Enter Phone
		 * 
		 */
		public void enterBranchPhone(String branch_phone)
		{
			this.test1.log(Status.INFO,"Enter branch phone");
			branchphone.clear();
			branchphone.sendKeys(branch_phone);
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
		 * To navigate to Edit Branch page
		 * 
		 */
		public void navEditBranch() {
			this.test1.log(Status.INFO,"Clicked on Edit link");
			iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
			lnkEdit.click();
			this.test1.log(Status.PASS,"Navigated to Edit Branch page");
		}
		
		/**
		 * To navigate to Delete Branch page
		 * 
		 */
		public void deleteBranch(boolean flag) {
			this.test1.log(Status.INFO,"Click on Delete link");
			iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
			lnkDelete.click();
			if(flag==true)
			{
				okbtn.click();
				this.test1.log(Status.PASS,"Deleted Branch");

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
		 * To create a Branch
		 * @param client_name
	     * @param branch_name
	     * @param branch_address
	     * @param branch_city
	     * @param branch_email
	     * @param branch_phone
		 * @param remarkText
		 */
		public void createBranch(String client_name,String branch_name,String branch_address,
				String branch_city,String branch_email,String branch_phone,String remarkText) {
			this.test1.log(Status.INFO,"Creating a Branch" +branch_name);
			selectClientname(client_name);
			enterBranchName(branch_name);
			enterBranchAddress(branch_address);
			enterBranchCity(branch_city);
			enterBranchEmail(branch_email);
			enterBranchPhone(branch_phone);
			enterRemark(remarkText);
			clicksaveandgoback();
			this.test1.log(Status.INFO,"Entered values in all field displayed on Add Branch page");
			this.test1.log(Status.PASS,branch_name+" Branch details stored successfully");
		}
		

		/**
		 * To Edit a Branch
	     * @param branch_name
	     * @param branch_address
	     * @param branch_city
	     * @param branch_email
	     * @param branch_phone
		 * @param remarkText
		 */
		public void editBranch(String branch_name,String branch_address,
				String branch_city,String branch_email,String branch_phone,String remarkText) {
			this.test1.log(Status.INFO,"Modifying a Branch" +branch_name);
			enterBranchName(branch_name);
			enterBranchAddress(branch_address);
			enterBranchCity(branch_city);
			enterBranchEmail(branch_email);
			enterBranchPhone(branch_phone);
			enterRemark(remarkText);
			clicksaveandgoback();
			this.test1.log(Status.PASS,branch_name+" Branch details modified successfully");
		}
		
		/**
		 * To search Branch
		 * @return 
		 * 
		 */
		public void searchBranch(String searchText) {
			iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
			this.test1.log(Status.INFO,"Searching a Branch by Search text :- " +searchText);
			searchBox.clear();
			searchBox.sendKeys(searchText);
			this.test1.log(Status.PASS,"Searched a Branch by Search text :- " +searchText);

		}
	
		/**
		 * To search Brand
		 * @return 
		 * 
		 */
		public boolean verifySearchBranch(String searchText) {
			boolean flag = false;
			iShuttleUtils.waitForElement(driver, Col3Row1, 10,this.test1);

			this.test1.log(Status.INFO,"verifying search results");
			try
			{
			if(Col3Row1.getText().equals(searchText))
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
