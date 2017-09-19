package com.ishuttle.pages;

import static org.testng.Assert.fail;

import java.util.List;
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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.utils.iShuttleUtils;

public class ClientPage extends LoadableComponent<ClientPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(xpath = ".//*[@id='flex1']/tbody/tr[1]/td[10]/ul/li[2]/a")
	WebElement lnkEdit;
	
	@FindBy(css = "span.export")
	WebElement btnExport;
	
	@FindBy(css = "span.print")
	WebElement btnPrint;
		
	@FindBy(linkText = "Detail")
	WebElement lnkDetail;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(id = "field-client_name")
	WebElement clientname;
	
	@FindBy(id = "field-client_code")
	WebElement clientcode;
	
	@FindBy(name = "client_type")
	WebElement clientType;
	
	@FindBy(id = "field-client_coordinator")
	WebElement clientcoordinator;
	
	@FindBy(id = "field-coordinator_email")
	WebElement coordinatoremail;
	
	@FindBy(id = "field-remark")
	WebElement remark;
			
	@FindBy(id = "save-and-go-back-button")
	WebElement btnsaveandgoback;

	@FindBy(id = "form-button-save")
	WebElement btnsave;

	@FindBy(id = "report-error")
	WebElement reporterror;
	
	@FindBy(xpath=".//*[@id='report-error']/p")
	WebElement Emailerror;
	
	@FindBy(xpath="//body/div[2]/div")
	WebElement SuccessMsg;
	
	@FindBy(css = "input.form-control.input-sm")
	WebElement searchBox;
	
	@FindBy(css = "table#flex1>tbody>tr>td:nth-child(2)")
	WebElement clientNameCol1Row1;
	
	@FindBy(css = "button.ajs-button.ajs-ok")
	WebElement okbtn;
	
	private WebDriver driver;
	private ExtentTest test1;
	/**
	 * 
	 * Constructor class for Client Page Here we initializing the driver for page factory 
	 * objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public ClientPage(WebDriver driver,ExtentTest test) {

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
		                    .withMessage("Client Master did not open up."))
		                    .until(ExpectedConditions.urlContains("configure/client"));
		            this.test1.log(Status.PASS,"Client Page is displayed");
		    		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		        } catch (TimeoutException e) {
		        	 driver.get(driver.getCurrentUrl());
		             (new WebDriverWait(driver, 20).pollingEvery(200,
		                     TimeUnit.MILLISECONDS).ignoring(
		                     NoSuchElementException.class)
		                     .withMessage("Client Master did not open up."))
		                     .until(ExpectedConditions.urlContains("configure/client"));
		        }
		}

	@Override
	protected void load() {
        isPageLoaded = true;
		
	}

	/**
	 * To navigate to Add client page
	 * 
	 */
	public void navAddClient() {
		this.test1.log(Status.INFO,"Clicked on Add Client button");
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
		btnAdd.click();
		this.test1.log(Status.PASS,"Navigated to Add Client page");
	}
	
	/**
	 * To navigate to Edit client page
	 * 
	 */
	public void navEditClient() {
		this.test1.log(Status.INFO,"Clicked on Edit link");
		iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
		lnkEdit.click();
		this.test1.log(Status.PASS,"Navigated to Edit Client page");
	}
	
	/**
	 * To navigate to Delete client page
	 * 
	 */
	public void deleteClient(boolean flag) {
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
	 * To enter Client Name
	 * 
	 * @param client
	 */
	public void enterClient(String client) {
		this.test1.log(Status.INFO,"Enter client name");
		clientname.clear();
		clientname.sendKeys(client);
	}
	
	/**
	 * To enter Client Code
	 * 
	 * @param code
	 */
	public void enterClientCode(String code) {
		this.test1.log(Status.INFO,"Enter Client Code");
		clientcode.clear();
		clientcode.sendKeys(code);
	}
	
	/**
	 * To enter client coordinator
	 * 
	 * @param client_coordinator
	 */
	public void enterClientCoordinator(String client_coordinator) {
		this.test1.log(Status.INFO,"Enter client coordinator");
		clientcoordinator.clear();
		clientcoordinator.sendKeys(client_coordinator);
	}
	
	/**
	 * To enter coordinator email
	 * 
	 * @param coordinator_email
	 */
	public void enterCoordinatorEmail(String coordinator_email) {
		this.test1.log(Status.INFO,"Enter Co-ordinator email");
		coordinatoremail.clear();
		coordinatoremail.sendKeys(coordinator_email);
	}
	
	/**
	 * To select a Client Type
	 * 
	 */
	public void selectClientType(String client_type) {
		JavascriptExecutor executor= (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('field-client_type').style.display='block';");
		Select select = new Select(clientType);
		select.selectByVisibleText(client_type);	
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
	/**
	 * To Click Save button
	 * 
	 */
	public void clickSave() {
		this.test1.log(Status.INFO,"Clicked on Save and Go back");
		iShuttleUtils.waitForElement(driver, btnsave, 10,this.test1);
		btnsave.click();
		iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
	}

	/**
	 * UCClient-01
	 * 
	 */
	public void UCClient01() {
		/*boolean bool=btnAdd.isEnabled();
		//Assert.assertEquals(true, bool);
		//System.out.println("export button present");
		if (bool==true)
		{
		System.out.println("createClient(String client,String code,String client_type,String client_coordinator,String coordinator_email, String remarkText)");
		btnAdd.click();
		}*/	
		btnAdd.click();
		WebElement x = driver.findElement(By.id("field-client_type"));
		
		//Select p= new Select(x);
		//List <WebElement> k= p.getOptions();
			//for(int i=0; i<k.size(); i++)
				//System.out.println(k.get(i).getText());
		
			String label=x.getText();
			System.out.println("Brand names are \t" + label );
	}	
	/**
	 * To Click Save button
	 * 
	 */
	public void WrongMail()
	
	{	   
		  String actual = driver.findElement(By.xpath(".//*[@id='report-error']/p")).getAttribute("innerHTML");
		  String expected = "The Coordinator Email field must contain a valid email address."; 
		  Assert.assertEquals(actual, expected);
	}
	
	/**
	 * To Click Save button
	 * 
	 */
	public void messageOnSaveandGoBack()
	
		{	   
		  String actual = SuccessMsg.getText();
		  String expected = "Your data has been successfully stored into the database. Edit Client"; 
		  Assert.assertEquals(actual, expected);
		  System.out.println(actual);
		  String title=driver.getCurrentUrl();
		  System.out.println(title);
		  Assert.assertTrue(title.contains("http://10.115.1.108/ishuttle-master/master/configure/client"));
				  
		}
	/**
	 *To Check Duplicate Client Code
	 * 
	 */
	public void DuplicateClientCode()
	
	{	   
		  String actual = driver.findElement(By.xpath(".//*[@id='report-error']/p")).getText();
		  String expected = "The Client field needs to hold a unique value."; 
		  Assert.assertEquals(actual, expected);
	}
	/** To create a client	
	 * @param client
	 * @param code
	 * @param client_type
	 * @param client_coordinator
	 * @param coordinator_email
	 * @param remarkText
	 */
	public void createClient(String client,String code,String client_type,String client_coordinator,String coordinator_email, String remarkText) {
		
		this.test1.log(Status.INFO,"Creating a Client" +client);
		this.test1.log(Status.INFO,"Enter values in all field displayed on Add Client page");
		enterClient(client);
		enterClientCode(code);
		selectClientType(client_type);
		enterClientCoordinator(client_coordinator);
		enterCoordinatorEmail(coordinator_email);
		enterRemark(remarkText);
		clicksaveandgoback();
		//messageOnSaveandGoBack();
		this.test1.log(Status.PASS,client+" Client details stored successfully");
	}
	/** To create a client
	 * @param client
	 * @param code
	 * @param client_type
	 * @param client_coordinator
	 * @param coordinator_email
	 * @param remarkText
	 */
	public void createClientwithSave(String client,String code,String client_type,String client_coordinator,String coordinator_email, String remarkText) {
		
		this.test1.log(Status.INFO,"Creating a Client" +client);
		this.test1.log(Status.INFO,"Enter values in all field displayed on Add Client page");
		enterClient(client);
		enterClientCode(code);
		selectClientType(client_type);
		enterClientCoordinator(client_coordinator);
		enterCoordinatorEmail(coordinator_email);
		enterRemark(remarkText);
		clickSave();
		this.test1.log(Status.PASS,client+" Client details stored successfully");
	}
	
	/**
	 * To edit a client
	 * @param client
	 * @param code
	 * @param client_type
	 * @param client_coordinator
	 * @param coordinator_email
	 * @param remarkText*/
	public void editClient(String client,String code,String client_type,String client_coordinator,String coordinator_email, String remarkText) {
		
		this.test1.log(Status.INFO,"Editing a Client" +client);
		this.test1.log(Status.INFO,"Modify values in the fields");
		enterClient(client);
		enterClientCode(code);
		selectClientType(client_type);
		enterClientCoordinator(client_coordinator);
		enterCoordinatorEmail(coordinator_email);
		enterRemark(remarkText);
		clicksaveandgoback();
		this.test1.log(Status.PASS,client+" Client details modified successfully");
	}
	
	/**
	 * To search Client
	 * @return 
	 * 
	 */
	public void searchClient(String searchText) {
		this.test1.log(Status.INFO,"Searching a Client by Search text :- " +searchText);
		searchBox.clear();
		searchBox.sendKeys(searchText);
		this.test1.log(Status.PASS,"Searched a Client by Search text :- " +searchText);

	}
	
	/**
	 * To search Client
	 * @return 
	 * 
	 */
	public boolean verifySearchClient(String searchText) {
		boolean flag = false;
		this.test1.log(Status.INFO,"verifying search results");
		try
		{
		if(clientNameCol1Row1.getText().equals(searchText))
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
