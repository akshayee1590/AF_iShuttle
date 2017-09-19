package com.ishuttle.pages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class DashboardPage extends LoadableComponent<DashboardPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(linkText = "MR")
	WebElement lnkMR;
	
	@FindBy(linkText = "Master")
	WebElement lnkMaster;
	
	@FindBy(linkText = "Client")
	WebElement lnkClient;
	
	@FindBy(linkText = "Brand")
	WebElement lnkBrand;
	
	@FindBy(linkText = "Branch")
	WebElement lnkBranch;
	
	@FindBy(linkText = "Contact")
	WebElement lnkContact;
	
	@FindBy(linkText = "Channel House")
	WebElement lnkChannelHouse;
	
	@FindBy(linkText = "Channel")
	WebElement lnkChannel;
	
	@FindBy(linkText = "Language")
	WebElement lnkLanguage;
	
	@FindBy(linkText = "Category")
	WebElement lnkCategory;
	
	@FindBy(linkText = "Sub Category")
	WebElement lnkSubCategory;
	
	@FindBy(linkText = "Assets")
	WebElement lnkAssets;
	
	@FindBy(linkText = "Order")
	WebElement lnkOrder;
	
	@FindBy(linkText = "Reports")
	WebElement lnkReports;
	
	@FindBy(linkText = "Asset Report")
	WebElement lnkAssetReport;
	
	@FindBy(linkText = "User Report")
	WebElement lnkUserReport;
	
	@FindBy(linkText = "Delivery Report")
	WebElement lnkDeliveryReport;
	
	@FindBy(linkText = "Activity Log")
	WebElement lnkActivityLog;

	@FindBy(linkText = "Menu Management")
	WebElement lnkMenuManagement;
	
	@FindBy(linkText = "Ion Auth")
	WebElement lnkIonAuth;
	
	@FindBy(linkText = "Users")
	WebElement lnkUsers;
	
	@FindBy(linkText = "Groups")
	WebElement lnkGroups;
	
	@FindBy(linkText = "Settings")
	WebElement lnkSettings;
	
	List<String> linksAll;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Dashboard Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public DashboardPage(WebDriver driver,ExtentTest test) {
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
		                    .withMessage("iShuttle Dashboard did not open up."))
		                    .until(ExpectedConditions.urlContains("/dashboard/index"));
		            this.test1.log(Status.PASS,"Dashboard is displayed");
		        } catch (TimeoutException e) {
		            iShuttleUtils.captureAndDisplayScreenShot(driver, test1);
		            this.test1.log(Status.FAIL,"Dashboard is not displayed <br>"+e);
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("iShuttle Dashboard did not open up."))
		                     .until(ExpectedConditions.urlContains("/dashboard/index"));
		        }
		}
		/**
		 * To click Master Link of Main Navigation
		 */
		public void clickOnMaster() {
			iShuttleUtils.waitForElement(driver, lnkMaster, 10,this.test1);
			lnkMaster.click();
			 this.test1.log(Status.INFO,"Clicked on Master");
			this.test1.log(Status.PASS,"Clicked on Master");
		}
		
		/**
		 * To click Client link on Master of Main Navigation
		 * @return 
		 */
		public ClientPage clickOnClient() {
			iShuttleUtils.waitForElement(driver, lnkClient, 10,this.test1);
			lnkClient.click();
			 this.test1.log(Status.INFO,"Clicked on Client");
			this.test1.log(Status.PASS,"Clicked on Master > Client");
         return new ClientPage(driver,this.test1).get();
		}
		
		/**
		 * To click Brand link on Master of Main Navigation
		 * @return 
		 */
		public BrandPage clickOnBrand() {
			iShuttleUtils.waitForElement(driver, lnkBrand, 10,this.test1);
			lnkBrand.click();
			 this.test1.log(Status.INFO,"Clicked on Brand");
			this.test1.log(Status.PASS,"Clicked on Brand");
         return new BrandPage(driver,this.test1).get();
		}
		

		/**
		 * To click Branch link on Master of Main Navigation
		 * @return 
		 */
		public BranchPage clickOnBranch() {
			iShuttleUtils.waitForElement(driver, lnkBranch, 10,this.test1);
			lnkBranch.click();
			 this.test1.log(Status.INFO,"Clicked on Branch");
			this.test1.log(Status.PASS,"Clicked on Branch");
         return new BranchPage(driver,this.test1).get();
		}
		
		/**
		 * To click Contact link on Master of Main Navigation
		 * @return 
		 */
		public ContactPage clickOnContact() {
			iShuttleUtils.waitForElement(driver, lnkContact, 10,this.test1);
			lnkContact.click();
			 this.test1.log(Status.INFO,"Clicked on Contact");
			this.test1.log(Status.PASS,"Clicked on Contact");
         return new ContactPage(driver,this.test1).get();
		}
		
		/**
		 * To click Channel House link on Master of Main Navigation
		 * @return 
		 */
		public ChannelHousePage clickOnChannelHouse() {
			iShuttleUtils.waitForElement(driver, lnkChannelHouse, 10,this.test1);
			lnkChannelHouse.click();
			 this.test1.log(Status.INFO,"Clicked on Channel House");
			this.test1.log(Status.PASS,"Clicked on Channel House");
         return new ChannelHousePage(driver,this.test1).get();
		}
		
		/**
		 * To click Channel link on Master of Main Navigation
		 * @return 
		 */
		public ChannelPage clickOnChannel() {
			iShuttleUtils.waitForElement(driver, lnkChannel, 10,this.test1);
			lnkChannel.click();
			 this.test1.log(Status.INFO,"Clicked on Channel");
			this.test1.log(Status.PASS,"Clicked on Channel");
         return new ChannelPage(driver,this.test1).get();
		}
	 
		/**
		 * To click Language link on Master of Main Navigation
		 * @return 
		 */
		public LanguagePage clickOnLanguage() {
			iShuttleUtils.waitForElement(driver, lnkLanguage, 10,this.test1);
			lnkLanguage.click();
			 this.test1.log(Status.INFO,"Clicked on Language");
			this.test1.log(Status.PASS,"Clicked on Language");
         return new LanguagePage(driver,this.test1).get();
		}
		

		/**
		 * To click Category link on Master of Main Navigation
		 * @return 
		 */
		public CategoryPage clickOnCategory() {
			iShuttleUtils.waitForElement(driver, lnkCategory, 10,this.test1);
			lnkCategory.click();
			 this.test1.log(Status.INFO,"Clicked on Category");
			this.test1.log(Status.PASS,"Clicked on Category");
         return new CategoryPage(driver,this.test1).get();
		}
		
		/**
		 * To click Sub Category link on Master of Main Navigation
		 * @return 
		 */
		public SubCategoryPage clickOnSubCategory() {
			 this.test1.log(Status.INFO,"Click on Sub Category");
			iShuttleUtils.waitForElement(driver, lnkSubCategory, 10,this.test1);
			lnkSubCategory.click();
			this.test1.log(Status.PASS,"Clicked on Sub Category");
         return new SubCategoryPage(driver,test1).get();
		}
		
		public boolean verifyMasterElement()
		{
			@SuppressWarnings("unused")
			boolean flag=false;
			iShuttleUtils.waitForElement(driver, lnkMaster, 10,this.test1);
			if(lnkMaster.isDisplayed()==true)
			{
				flag=true;
			}
			return true;
		}
	 
		/**
		 * To click Asset Link of Main Navigation
		 */
	
		public void clickOnAssets() {
			iShuttleUtils.waitForElement(driver, lnkAssets, 10,this.test1);
			lnkAssets.click();
			 this.test1.log(Status.INFO,"Clicked on Assets");
			this.test1.log(Status.PASS,"Clicked on Assets");
		}
		/**
		 * To click Order Link of Main Navigation
		 */

		public void clickOnOrder() {
			iShuttleUtils.waitForElement(driver, lnkOrder, 10,this.test1);
			lnkOrder.click();
			 this.test1.log(Status.INFO,"Clicked on Order");
			this.test1.log(Status.PASS,"Clicked on Order");
		}
		/**
		 * To click Reports Link of Main Navigation
		 * @return 
		 */
		
		public void clickOnReports() {
			iShuttleUtils.waitForElement(driver, lnkReports, 10,this.test1);
			lnkReports.click();
			 this.test1.log(Status.INFO,"Clicked on Reports");
			this.test1.log(Status.PASS,"Clicked on Reports");
		}
		/**
		 * To click Activity Log Link of Main Navigation
		 */
		
		public void clickOnActivityLog() {
			iShuttleUtils.waitForElement(driver, lnkActivityLog, 10,this.test1);
			lnkActivityLog.click();
			 this.test1.log(Status.INFO,"Clicked on Activity Log");
			this.test1.log(Status.PASS,"Clicked on Activity Log");
		}
		/**
		 * To click Menu Management Link of Main Navigation
		 */
	
		public void clickOnMenuManagement() {
			iShuttleUtils.waitForElement(driver, lnkMenuManagement, 10,this.test1);
			lnkMenuManagement.click();
			 this.test1.log(Status.INFO,"Clicked on Menu Management");
			this.test1.log(Status.PASS,"Clicked on Menu Management");
		}
		/**
		 * To click Ion Auth Link of Main Navigation
		 */
	 
		public void clickOnIonAuth() {
			iShuttleUtils.waitForElement(driver, lnkIonAuth, 10,this.test1);
			lnkIonAuth.click();
			 this.test1.log(Status.INFO,"Clicked on Ion Auth");
			this.test1.log(Status.PASS,"Clicked on Ion Auth");
		}
		/**
		 * To click Settings Link of Main Navigation
		 */
	 
		public void clickOnSettings() {
			iShuttleUtils.waitForElement(driver, lnkSettings, 10,this.test1);
			lnkSettings.click();
			 this.test1.log(Status.INFO,"Clicked on Settings");
			this.test1.log(Status.PASS,"Clicked on Settings");
		}

		
		/**
		 * To click MR Link of Main Navigation
		 */
		public MRPage clickOnMR() {
			iShuttleUtils.waitForElement(driver, lnkMR, 10,this.test1);
			lnkMR.click();
			 this.test1.log(Status.INFO,"Clicked on MR");
			this.test1.log(Status.PASS,"Clicked on MR");
         return new MRPage(driver,this.test1).get();
		}
		
		/**
		 * To click Asset Link of Main Navigation
		 * @return 
		 */
	
		public AssetReportPage clickOnAssetReport() {
			iShuttleUtils.waitForElement(driver, lnkAssetReport, 10,this.test1);
			lnkAssetReport.click();
			 this.test1.log(Status.INFO,"Clicked on Assets");
			this.test1.log(Status.PASS,"Clicked on Assets");
			return new AssetReportPage(driver,this.test1).get();
		}
		/**
		 * To click Reports Link of Main Navigation
		 * @return 
		 */
		
		public UserReportPage clickOnUserReport() {
			iShuttleUtils.waitForElement(driver, lnkUserReport, 10,this.test1);
			lnkUserReport.click();
			 this.test1.log(Status.INFO,"Clicked on Reports");
			this.test1.log(Status.PASS,"Clicked on Reports");
			return new UserReportPage(driver,this.test1).get();
		}
		/**
		 * To click Reports Link of Main Navigation
		 */
		
		public DeliveryReportPage clickOnDeliveryReport() {
			iShuttleUtils.waitForElement(driver, lnkDeliveryReport, 10,this.test1);
			lnkDeliveryReport.click();
			 this.test1.log(Status.INFO,"Clicked on Reports");
			this.test1.log(Status.PASS,"Clicked on Reports");
			return new DeliveryReportPage(driver,this.test1).get();
		}

		/**
		 * To click Users Link of Main Navigation
		 * @return 
		 */
		
		public UsersPage clickOnUsers() {
			iShuttleUtils.waitForElement(driver, lnkUsers, 10,this.test1);
			lnkUsers.click();
			 this.test1.log(Status.INFO,"Clicked on Users");
			this.test1.log(Status.PASS,"Clicked on lnkUsers");
			return new UsersPage(driver,this.test1).get();
		}
		
		/**
		 * To click Users Link of Main Navigation
		 */
		
		public void clickOnGroups() {
			iShuttleUtils.waitForElement(driver, lnkGroups, 10,this.test1);
			lnkGroups.click();
			 this.test1.log(Status.INFO,"Clicked on Groups");
			this.test1.log(Status.PASS,"Clicked on lnkGroups");
			//return new GroupsPage(driver,this.test1).get();
		}
		
		
		
		
	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	

}
