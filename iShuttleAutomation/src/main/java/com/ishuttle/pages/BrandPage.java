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
public class BrandPage extends LoadableComponent<BrandPage> {
	
	private boolean isPageLoaded;

	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(name = "client_id")
	WebElement clientname;

	@FindBy(id = "field-brand_name")
	WebElement brandname;
	
	@FindBy(id = "field-remark")
	WebElement remark;
	
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;
	
	@FindBy(css = "table#flex1>tbody>tr>td:nth-child(3)")
	WebElement clientNameCol3Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Brand Page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public BrandPage(WebDriver driver,ExtentTest test) {

	        this.driver = driver;
	        this.test1=test;
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
		                    .withMessage("Brand Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/brand"));
					iShuttleUtils.waitForElement(driver, btnAdd, 10,test1);
		             this.test1.log(Status.PASS,"Brand Page is displayed");

		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Brand Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/brand"));
		        }
		}
		

		
	

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add Brand page
	 * 
	 */
	public void navAddBrand() {
		 this.test1.log(Status.INFO,"Clicked on Add Brand button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		 this.test1.log(Status.PASS,"Navigated to Add Brand page");
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
	 * To enter brand name
	 * 
	 * @param brand_name
	 */
	public void enterBrandName(String brand_name) {
		 this.test1.log(Status.INFO,"Enter brand name");
		brandname.clear();
		brandname.sendKeys(brand_name);
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
	 * To navigate to Edit Brand page
	 * 
	 */
	public void navEditBrand() {
		 this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		 this.test1.log(Status.PASS,"Navigated to Edit Brand page");
	}
	
	/**
	 * To navigate to Delete Brand page
	 * 
	 */
	public void deleteBrand(boolean flag) {
		 this.test1.log(Status.INFO,"Click on Delete link");
		iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
		lnkDelete.click();
		if(flag==true)
		{
			okbtn.click();
			 this.test1.log(Status.PASS,"Deleted Client");

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
	 * To create a client
	 * @param client_name
     * @param brand_name
	 * @param remarkText
	 */
	public void createBrand(String client_name,String brand_name,String remarkText) {
		
		 this.test1.log(Status.INFO,"Creating a Brand" +brand_name);
		selectClientname(client_name);
		enterBrandName(brand_name);
		enterRemark(remarkText);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,"Entered values in all field displayed on Add Brand page");
		 this.test1.log(Status.PASS,brand_name+" Brand details stored successfully");
	}
	
	/**
	 * To edit a Brand
	 * @param brand_name
	 * @param remarkText
	 **/
	public void editBrand(String brand_name,String remarkText) {
		 this.test1.log(Status.INFO,"Editing a Brand" +brand_name);
		 this.test1.log(Status.PASS,"Modify values in the fields");
		enterBrandName(brand_name);
		enterRemark(remarkText);
		clicksaveandgoback();
		 this.test1.log(Status.PASS,brand_name+" Brand details modified successfully");
	}
	
	/**
	 * To search Brand
	 * @return 
	 * 
	 */
	public void searchBrand(String searchText) {
		iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
		 this.test1.log(Status.INFO,"Searching a Brand by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		 this.test1.log(Status.PASS,"Searched a Brand by Search text :- " +searchText);

	}
	
	/**
	 * To search Brand
	 * @return 
	 * 
	 */
	public boolean verifySearchBrand(String searchText) {
		boolean flag = false;
		iShuttleUtils.waitForElement(driver, clientNameCol3Row1, 10,this.test1);

		 this.test1.log(Status.INFO,"verifying search results");
		try
		{
		if(clientNameCol3Row1.getText().equals(searchText))
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
