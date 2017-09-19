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

public class UsersPage extends LoadableComponent<UsersPage> {
	
	private boolean isPageLoaded;
	
	@FindBy(css = "span.add")
	WebElement btnAdd;
	
	@FindBy(linkText = "Edit")
	WebElement lnkEdit;
	
	@FindBy(linkText = "Delete")
	WebElement lnkDelete;
	
	@FindBy(id = "field-username")
	WebElement username;
	
	@FindBy(name = "first_name")
	WebElement firstname;
	
	@FindBy(name = "last_name")
	WebElement lastname;
	
	@FindBy(id = "field-email")
	WebElement Useremail;
	
	@FindBy(id = "field-phone")
	WebElement Userphone;
	
	@FindBy(id = "field-entity_id")
	WebElement entity_id;

	@FindBy(id = "field-groups")
	WebElement user_Type;

	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "password_confirm")
	WebElement passwordconfirm;
	
	@FindBy(name = "old_password")
	WebElement oldpwd;
	
	@FindBy(name = "new_password")
	WebElement newpwd;
	
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
	 * Constructor class for Users page Here we initializing the driver for page factory objects and specific wait time for Ajax element
	 * 
	 * @param driver
	 */
	 public UsersPage(WebDriver driver,ExtentTest test) {
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
	        isPageLoaded = true;
			
		}

		/**
		 * To navigate to Add Users page
		 * 
		 */
		public void navAddUser() {
			
			this.test1.log(Status.INFO,"Clicked on Add Users button");
			iShuttleUtils.waitForElement(driver, btnAdd, 10,this.test1);
			btnAdd.click();
			this.test1.log(Status.PASS,"Navigated to Add Users page");
		}

		/**
		 * To Enter User Name
		 * 
		 */
		public void enterUserName(String user_name)
		{
			this.test1.log(Status.INFO,"Enter user name");
			username.clear();
			username.sendKeys(user_name);
		}
		/**
		 * To Enter First Name
		 * 
		 */
		public void enterfirstname(String first_name)
		{
			this.test1.log(Status.INFO,"Enter first name");
			firstname.clear();
			firstname.sendKeys(first_name);
		}
		/**
		 * To Enter Last Name
		 * 
		 */
		public void enterlastname(String last_name)
		{
			this.test1.log(Status.INFO,"Enter last name");
			lastname.clear();
			lastname.sendKeys(last_name);
		}
		/**
		 * To Enter Email
		 * 
		 */
		public void enterUseremail(String User_email)
		{
			this.test1.log(Status.INFO,"Enter User email");
			Useremail.clear();
			Useremail.sendKeys(User_email);
		}
		
		/**
		 * To Enter Phone
		 * 
		 */
		public void enterUserphone(String User_phone)
		{
			this.test1.log(Status.INFO,"Enter User phone");
			Userphone.clear();
			Userphone.sendKeys(User_phone);
		}
		/**
		 * To Select Associated Entity
		 * 
		 */
		public void selectAssociatedEntity(String entityid) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('field-entity_id').style.display='block';");
			Select select = new Select(entity_id);
			select.selectByVisibleText(entityid);
		}
		/**
		 * To Select User Type
		 * 
		 */
		public void selectUserType(String UserType) {
			JavascriptExecutor executor= (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('field-groups').style.display='block';");
			Select select = new Select(user_Type);
			select.selectByVisibleText(UserType);
		}
		/**
		 * To Enter Phone
		 * 
		 */
		public void enterPassword(String pwd)
		{
			this.test1.log(Status.INFO,"Enter User Password");
			password.clear();
			password.sendKeys(pwd);
		}
		/**
		 * To Enter Phone
		 * 
		 */
		public void enterConfirmPassword(String confirmPwd)
		{
			this.test1.log(Status.INFO,"Enter confirm password");
			passwordconfirm.clear();
			passwordconfirm.sendKeys(confirmPwd);
		}

		/**
		 * To Enter Old Password
		 * 
		 */
		public void enterOldPassword(String old_pwd)
		{
			this.test1.log(Status.INFO,"Enter User Password");
			oldpwd.clear();
			oldpwd.sendKeys(old_pwd);
		}
		/**
		 * To Enter New Password
		 * 
		 */
		public void enterNewPassword(String new_Pwd)
		{
			this.test1.log(Status.INFO,"Enter confirm password");
			newpwd.clear();
			newpwd.sendKeys(new_Pwd);
		}

		/**
		 * To navigate to Edit User page
		 * 
		 */
		public void navEditUser() {
			this.test1.log(Status.INFO,"Clicked on Edit link");
			iShuttleUtils.waitForElement(driver, lnkEdit, 10,this.test1);
			lnkEdit.click();
			this.test1.log(Status.PASS,"Navigated to Edit User page");
		}
		
		/**
		 * To navigate to Delete User page
		 * 
		 */
		public void deleteUser(boolean flag) {
			this.test1.log(Status.INFO,"Click on Delete link");
			iShuttleUtils.waitForElement(driver, lnkDelete, 10,this.test1);
			lnkDelete.click();
			if(flag==true)
			{
				okbtn.click();
				this.test1.log(Status.PASS,"Deleted User");

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
		 * To create a User
		 * @param user_name
		 * @param first_name
		 * @param last_name
		 * @param User_email
		 * @param User_phone
		 * @param entityid
		 * @param UserType
		 * @param pwd
		 * @param confirmPwd		 
		 **/
		
		public void createUser(String user_name,String first_name,String last_name,
				String User_email,String User_phone,String entityid,String UserType, String pwd, String confirmPwd ) 
		{
			this.test1.log(Status.INFO,"Creating a User" +user_name);
			enterUserName(user_name);
			enterfirstname(first_name);
			enterlastname(last_name);
			enterUseremail( User_email);
			enterUserphone(User_phone);
			selectAssociatedEntity(entityid);
			selectUserType(UserType);
			 enterPassword(pwd);
			enterConfirmPassword(confirmPwd);
			clicksaveandgoback();
			this.test1.log(Status.INFO,"Entered values in all field displayed on Add User page");
			this.test1.log(Status.PASS,user_name+" Branch details stored successfully");
		}
		

		/**
		 * To Edit a uSER
	     * @param user_name
		 * @param first_name
		 * @param last_name
		 * @param User_email
		 * @param User_phone
		 * @param entityid
		 * @param UserType
		 * @param pwd
		 * @param confirmPwd		 
		 **/

		public void editUser(String user_name,String first_name,String last_name,
				String User_email,String User_phone,String entityid,String UserType, String old_Pwd, String new_Pwd ) 
		{
			this.test1.log(Status.INFO,"Modifying a User" +user_name);
			enterUserName(user_name);
			enterfirstname(first_name);
			enterlastname(last_name);
			enterUseremail( User_email);
			enterUserphone(User_phone);
			selectAssociatedEntity(entityid);
			selectUserType(UserType);
			enterOldPassword(old_Pwd);
			enterNewPassword(new_Pwd);
			clicksaveandgoback();
			this.test1.log(Status.PASS,user_name+" User details modified successfully");
		}
		/**
		 * To search User
		 * @return 
		 * 
		 */
		public void searchUser(String searchText) {
			iShuttleUtils.waitForElement(driver, searchBox, 10,this.test1);
			this.test1.log(Status.INFO,"Searching an User by Search text :- " +searchText);
			searchBox.clear();
			searchBox.sendKeys(searchText);
			this.test1.log(Status.PASS,"Searched an User by Search text :- " +searchText);

		}
	
		/**
		 * To search User
		 * @return 
		 * 
		 */
		public boolean verifySearchUser(String searchText) {
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
