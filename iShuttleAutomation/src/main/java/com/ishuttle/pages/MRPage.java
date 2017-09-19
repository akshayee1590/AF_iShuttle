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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;


public class MRPage extends LoadableComponent<MRPage> {
	
	public static final String AssetPage = null;

	private boolean isPageLoaded;
	
	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(name = "client_id")
	WebElement clientname;
	
	@FindBy(name = "brand_id")
	WebElement brandname;
	
	@FindBy(name = "billingclient_id")
	WebElement billingClientID;
	
	@FindBy(id = "field-requester_contact")
	WebElement requesterContact;

	@FindBy(id = "field-mr_date")
	WebElement mrDate;
	
	@FindBy(id = "field-cc_email")
	WebElement ccEmail;
	
	@FindBy(id = "field-campaign_name")
	WebElement campaignName;
	
	@FindBy(name = "agency_id")
	WebElement agencyID;
	
	@FindBy(name = "creativeagency_id")
	WebElement creativegencyID;

	@FindBy(id = "field-remark")
	WebElement remark;
			
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(xpath = "/html/body/div/div/section[2]/div[3]/div/div/div[3]/div[2]/div/form/div[1]/div/div/div[2]/div/table/tbody/tr[1]/td[10]/ul/li[1]/a")
	static
	WebElement lnkAsset;
	
	@FindBy(xpath = "/html/body/div/div/section[2]/div[3]/div/div/div[3]/div[2]/div/form/div[1]/div/div/div[2]/div/table/tbody/tr[1]/td[10]/ul/li[2]/a")
	static
	WebElement createOrder;
	
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
	
	private static WebDriver driver;
	private static ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for MR page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public MRPage(WebDriver driver,ExtentTest test) {
		 
		    MRPage.test1=test;
	        MRPage.driver = driver;
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
		                    .withMessage("MR Page did not open up."))
		                    .until(ExpectedConditions.urlContains("mrcontrol"));
		            MRPage.test1.log(Status.PASS,"MR Page is displayed");
		    		iShuttleUtils.waitForElement(driver, btnAdd,10,MRPage.test1);
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("MR page did not open up."))
		                     .until(ExpectedConditions.urlContains("mrcontrol"));
		        }
		}

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add MR page
	 * 
	 */
	public void navAddMR() {
		MRPage.test1.log(Status.INFO,"Clicked on Add MR button");
		iShuttleUtils.waitForElement(driver, btnAdd,10,MRPage.test1);
		btnAdd.click();
		MRPage.test1.log(Status.PASS,"Navigated to Add MR page");
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
	 * To Select a Brand name
	 * 
	 */
	public void selectBrandname(String brand_name) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("var x=document.getElementsByName('brand_id'); x[0].style.display='block';");
		Select select = new Select(brandname);
		select.selectByVisibleText(brand_name);
	}
	
	/**
	 * To Select a Billing Client name
	 * 
	 */
	public void selectBillingClientname(String billingClient_name) {
		MRPage.test1.log(Status.INFO,"Select Client Name");
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-billingclient_id').style.display='block';");
		Select select = new Select(billingClientID);
		select.selectByVisibleText(billingClient_name);
	}
	
	/**
	 * To Enter MR Date
	 * 
	 */
	public void enterMRDate(String mr_date)
	{
		MRPage.test1.log(Status.INFO,"Enter MR Date");
		mrDate.clear();
		mrDate.sendKeys(mr_date);
	}
	
	/**
	 * To Enter Co-Ordinator
	 * 
	 */
	public void enterCoOrdinator(String coOrdinator)
	{
		MRPage.test1.log(Status.INFO,"Enter Co-Ordinator");
		requesterContact.clear();
		requesterContact.sendKeys(coOrdinator);
	}
	
	/**
	 * To Enter Billing Client Email
	 * 
	 */
	public void enterCCEmail(String billClientEmail)
	{
		MRPage.test1.log(Status.INFO,"Enter Billing Client Email");
		ccEmail.clear();
		ccEmail.sendKeys(billClientEmail);
	}
	
	/**
	 * To Enter Campaign Name
	 * 
	 */
	public void enterCampaignName(String campaign_name)
	{
		MRPage.test1.log(Status.INFO,"Enter Campaign Name");
		campaignName.clear();
		campaignName.sendKeys(campaign_name);
	}
	
	/**
	 * To navigate to Edit MR page
	 * 
	 */
	public void navEditMR() {
		MRPage.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit,10,MRPage.test1);
		lnkEdit.click();
		MRPage.test1.log(Status.PASS,"Navigated to Edit MR page");
	}
	
	/**
	 * To Select a Media Agency
	 * 
	 */
	public void selectMediaAgency(String media_agency) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-agency_id').style.display='block';");
		Select select = new Select(agencyID);
		select.selectByVisibleText(media_agency);
	}
	
	/**
	 * To Select a Creative Agency
	 * 
	 */
	public void selectCreativeAgency(String creative_agency) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-creativeagency_id').style.display='block';");
		Select select = new Select(creativegencyID);
		select.selectByVisibleText(creative_agency);
	}
	
	/**
	 * To enter Remark
	 * 
	 * @param text
	 */
	public void enterRemark(String text) {
		MRPage.test1.log(Status.INFO,"Enter Remark");
		remark.clear();
		remark.sendKeys(text);
	}

	/**
	 * To Click Save and Go back to list button
	 * 
	 */
	public void clicksaveandgoback() {
		MRPage.test1.log(Status.INFO,"Clicked on Save and Go back");
		iShuttleUtils.waitForElement(driver, btnsaveandgoback,10,MRPage.test1);
		btnsaveandgoback.click();
		iShuttleUtils.waitForElement(driver, btnAdd,10,MRPage.test1);

	}
	

	
	
	/**
	 * To create a MR
	 * @param client
	 * @param brand_name
	 * @param billingClient_name
	 * @param mr_date
	 * @param coOrdinator
	 * @param billClientEmail
	 * @param campaign_name
	 * @param media_agency
	 * @param creative_agency
	 * @param remarkText
	 */
	public void createMR(String client,String brand_name,String billingClient_name,String mr_date,
			String coOrdinator,String billClientEmail,String campaign_name,String media_agency,
			String creative_agency,String remarkText) {
		
		MRPage.test1.log(Status.INFO,"Creating a MR" +client);
		MRPage.test1.log(Status.PASS,"Enter values in all field displayed on Add MR page");
		selectClientname(client);
		selectBrandname(brand_name);
		selectBillingClientname(billingClient_name);
		enterMRDate(mr_date);
		enterCoOrdinator(coOrdinator);
		enterCCEmail(billClientEmail);
		enterCampaignName(campaign_name);
		selectMediaAgency(media_agency);
		selectCreativeAgency(creative_agency);
		enterRemark(remarkText);
		clicksaveandgoback();
		MRPage.test1.log(Status.PASS,client+" MR details stored successfully");
	}
	

	/**
	 * To Edit a MR
	 * @param code
	 * @param client_type
	 * @param client_coordinator
	 * @param coordinator_email
	 * @param remarkText
	 */
	public void editMR(String mr_date,
			String coOrdinator,String billClientEmail,String campaign_name,String media_agency,
			String creative_agency,String remarkText) {
		
		MRPage.test1.log(Status.INFO,"Editing a MR");
		MRPage.test1.log(Status.PASS,"Enter values in all field displayed on Add MR page");
		enterMRDate(mr_date);
		enterCoOrdinator(coOrdinator);
		enterCCEmail(billClientEmail);
		enterCampaignName(campaign_name);
		selectMediaAgency(media_agency);
		selectCreativeAgency(creative_agency);
		enterRemark(remarkText);
		clicksaveandgoback();
		MRPage.test1.log(Status.PASS,"MR details stored successfully");
	}

	
	/**
	 * To search MR
	 * @return 
	 * 
	 */
	public void searchClient(String searchText) {
		MRPage.test1.log(Status.INFO,"Searching a MR by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		MRPage.test1.log(Status.PASS,"Searched a MR by Search text :- " +searchText);

	}
	
	/**
	 * To search MR
	 * @return 
	 * 
	 */
	public boolean verifySearchMR(String searchText) {
		boolean flag = false;
		MRPage.test1.log(Status.INFO,"verifying search results");
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
	
	public static AssetPage clickOnAsset() {
		iShuttleUtils.waitForElement(driver, lnkAsset, 10,test1);
		lnkAsset.click();
		test1.log(Status.INFO,"Clicked on Asset");
		test1.log(Status.PASS,"Clicked on Asset");
		return new AssetPage(driver,test1).get();
					
		}
	public static CreateOrder clickOnCreateOrder() {
		iShuttleUtils.waitForElement(driver, createOrder, 10,test1);
		createOrder.click();
		test1.log(Status.INFO,"Clicked on Asset");
		test1.log(Status.PASS,"Clicked on Asset");
		return new CreateOrder(driver,test1).get();
					
		}

	
	
}