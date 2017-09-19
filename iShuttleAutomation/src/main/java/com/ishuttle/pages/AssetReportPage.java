package com.ishuttle.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;


public class AssetReportPage extends LoadableComponent<AssetReportPage> {
	
	@FindBy(id = "client_id")    
	WebElement lnkClient;
	
	@FindBy(id = "brand_id")
	WebElement lnkBrand;
	
	@FindBy(id = "field-asset_no")
	WebElement lnkAssetNo;
	
	@FindBy(id = "field-title")
	WebElement lnkTitle;
	
	@FindBy(id = "language_id")
	WebElement lnkLanguages;
		
	@FindBy(id = "form-button-save")
	WebElement btnProceed;
	
	@FindBy(css = "cancel-button")
	WebElement cancelbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	
	/**
	 * 
	 * Constructor class for Branch page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public AssetReportPage(WebDriver driver,ExtentTest test) {
		    this.test1=test;
	        this.driver = driver;
	        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
	                iShuttleUtils.iShuttleMinElementWait);
	        PageFactory.initElements(finder, this);
	    }


		@Override
		protected void isLoaded() throws Error {
			// TODO Auto-generated method stub
			}
		
		@Override
		protected void load() {
			
		}
		
		/**
		 * To Select a Client 
		 * 
		 */
		public void selectClient(String client) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('client_id').style.display='block';");
			Select select = new Select(lnkClient);
			select.selectByVisibleText(client);
		}
		/**
		 * To Enter Brand Name
		 * brand_id
		 */
		public void selectBrand(String brand)
		{
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('brand_id').style.display='block';");
			Select select = new Select(lnkBrand);
			select.selectByVisibleText(brand);
		}
		
		/**
		 * To Enter Asset No
		 * 
		**/
		public void enterAssetNo(String AssetNo)
		{
			this.test1.log(Status.INFO,"Enter Asset No");
			lnkAssetNo.clear();
			lnkAssetNo.sendKeys(AssetNo);
		}
		
		/**
		 * To Enter Title/Caption
		 * 
		 */
		public void enterTitle(String Title)
		{
			this.test1.log(Status.INFO,"Enter Title");
			lnkTitle.clear();
			lnkTitle.sendKeys(Title);
		}
		
		/**
		 * To Enter Language
		 *language_id 
		 */
		public void selectLanguages(String Language)
		{
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('language_id').style.display='block';");
			Select select = new Select(lnkLanguages);
			select.selectByVisibleText(Language);
	}
		/**
		 * To Click Proceed button
		 * 
		 */
		public void clicksOnProceed() {
			this.test1.log(Status.INFO,"Clicked on Save and Go back");
			iShuttleUtils.waitForElement(driver, btnProceed, 10,this.test1);
			btnProceed.click();
			iShuttleUtils.waitForElement(driver, btnProceed, 10,this.test1);

		}
		
				
		
		/**
		 * To Generate a Asset report
		 * @param client
	     * @param brand
	     * @param Title
	     * @param Language
	    		 */
		public void createAssetReportPage(String client,String brand,String AssetNo, String Title,String Language) 	
		{
			this.test1.log(Status.INFO,"Generating Asset report for client" +client);
			selectClient(client);
			selectBrand(brand);
			enterAssetNo(AssetNo);
			enterTitle(Title);
			selectLanguages(Language);
			clicksOnProceed();
			this.test1.log(Status.INFO,"Entered values in all field displayed on Asset Report page");
			this.test1.log(Status.PASS,client+" Asset Reports details stored successfully");
		}



}
