package com.ishuttle.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.pages.ClientPage;
import com.ishuttle.pages.DashboardPage;
import com.ishuttle.pages.LoginPage;
import com.ishuttle.utils.ExtentManager;
import com.ishuttle.utils.ReadCSVData;
import com.ishuttle.utils.ReadExcel;
import com.ishuttle.utils.WebDriverFactory;
import com.ishuttle.utils.iShuttleUtils;

import ch.qos.logback.core.net.SyslogOutputStream;
public class SmokeSuite3 {
    private String webSite;
    private ExtentReports extent;
    public String adminUsername="";
    public String adminPassword="";
    boolean loginDataFlag=false;
    HashMap<String, String> map= new HashMap<>();
    
	@FindBy(linkText = "Master")
	WebElement lnkMaster;
    
    @BeforeClass
	public void M1()
    {
		extent = ExtentManager.GetExtent();
	}
    
    @BeforeTest
    public void init(ITestContext context) 
    {
    	
        webSite = System.getProperty("webSite") != null ? System
                .getProperty("webSite") : context.getCurrentXmlTest()
                .getParameter("webSite").toLowerCase();
    }

   
    
    
    
    /* New */
    
    
    
    
    
    
	//@Test(description = "Verify User Rights of Admin")
    @Parameters({"browserName","webSite"})
    public void smoketest0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify User Rights of Admin  " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
            
        	LoginPage loginPage = new LoginPage(driver, webSite,test).get();

            DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
		    iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("Admin is able to Login");
		    }else
		    {
		    	 test.fail("Admin is not able to login"); 
		    }
		    
		   
		    
		    dashBoardpage.clickOnMaster();
		    
		    
		    dashBoardpage.clickOnBranch();
            dashBoardpage.clickOnBrand();
            dashBoardpage.clickOnChannel();
            dashBoardpage.clickOnContact();
            dashBoardpage.clickOnClient();
            dashBoardpage.clickOnLanguage();
            dashBoardpage.clickOnSubCategory();
            dashBoardpage.clickOnCategory();
            dashBoardpage.clickOnChannelHouse();
		    dashBoardpage.clickOnMR();
            dashBoardpage.clickOnAssets();
            dashBoardpage.clickOnOrder();
            dashBoardpage.clickOnReports();
            dashBoardpage.clickOnActivityLog();
            dashBoardpage.clickOnIonAuth();
            dashBoardpage.clickOnMenuManagement();
            dashBoardpage.clickOnSettings();
            
    }
	
	

	//@Test(description = "Verify User Rights of HOD")
    @Parameters({"browserName","webSite"})
    public void smoketest0002(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	
        // Get the web driver instance
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
       // Object[][] testData=ReadCSVData.readData("HOD Login");
        
		
        Object[][] testData=ReadExcel.getExcelData("HOD Login");

        int k=0;
	    for(int i=0; i<testData.length;i++)
	    {
	    	k=0;
	      
	       if(method.getName().equals(testData[i][k].toString()))
	       {
	       String[] newtestdata=testData[i][k+1].toString().split(",");
	       adminUsername=newtestdata[0];
	       adminPassword=newtestdata[1];
	       context.setAttribute("username", adminUsername);
	       context.setAttribute("password", adminPassword);
	       break;
	       }
	    }
	    test = extent.createTest(method.getName()," Verify User Rights of HOD  " + " <small><b><i>[" + browser
				+ "]</b></i></small>");
        // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("HOD is able to Login");
		    }else
		    {
		    	 test.fail("HOD is not able to login"); 
		    }
		    
            dashBoardpage.clickOnReports();
            dashBoardpage.clickOnActivityLog();
            
    	}
	
    
  
	//@Test(description = "Verify User Rights of Operator")
    @Parameters({"browserName","webSite"})
    public void smoketest0003(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	
        // Get the web driver instance
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
       // Object[][] testData=ReadExcel.getExcelData("Operator Login");
        Object[][] testData=ReadCSVData.readData("Asset Report");
        
        int k=0;
	    for(int i=0; i<testData.length;i++)
	    {
	    	k=0;
	      
	       if(method.getName().equals(testData[i][k].toString()))
	       {
	       String[] newtestdata=testData[i][k+1].toString().split(",");
	       adminUsername=newtestdata[0];
	       adminPassword=newtestdata[1];
	       context.setAttribute("username", adminUsername);
	       context.setAttribute("password", adminPassword);
	       break;
	       }
	    }
	    test = extent.createTest(method.getName()," Verify User Rights of Operator  " + " <small><b><i>[" + browser+ "]</b></i></small>");
	    // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("Operator is able to Login");
		    }else
		    {
		    	 test.fail("Operator is not able to login"); 
		    }
		    
            dashBoardpage.clickOnAssets();
            dashBoardpage.clickOnOrder();
            dashBoardpage.clickOnMaster();
            dashBoardpage.clickOnBranch();
            dashBoardpage.clickOnBrand();
            dashBoardpage.clickOnChannel();
            dashBoardpage.clickOnContact();
            dashBoardpage.clickOnClient();
            dashBoardpage.clickOnLanguage();
            dashBoardpage.clickOnSubCategory();
            dashBoardpage.clickOnCategory();
            dashBoardpage.clickOnReports();
            
    	}

	//@Test(description = "Verify User Rights of Agency")
    @Parameters({"browserName","webSite"})
    public void smoketest0004(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
       // Object[][] testData=ReadExcel.getExcelData("Agency Login");
        Object[][] testData=ReadCSVData.readData("Agency Login");
        
        int k=0;
	    for(int i=0; i<testData.length;i++)
	    {
	    	k=0;
	      
	       if(method.getName().equals(testData[i][k].toString()))
	       {
	       String[] newtestdata=testData[i][k+1].toString().split(",");
	       adminUsername=newtestdata[0];
	       adminPassword=newtestdata[1];
	       context.setAttribute("username", adminUsername);
	       context.setAttribute("password", adminPassword);
	       break;
	       }
	    }
	        
        test = extent.createTest(method.getName()," Verify User Rights of Agency " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();

            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("Agency is able to Login");
		    }else
		    {
		    	 test.fail("Agency is not able to login"); 
		    }
		    
            dashBoardpage.clickOnAssets();
            dashBoardpage.clickOnOrder();
            dashBoardpage.clickOnReports();
             }
    
	//@Test(description = "Verify User Rights of Member")
    @Parameters({"browserName","webSite"})
    public void smoketest0005(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
       Object[][] testData=ReadExcel.getExcelData("Member Login");
        
        int k=0;
	    for(int i=0; i<testData.length;i++)
	    {
	    	k=0;
	      
	       if(method.getName().equals(testData[i][k].toString()))
	       {
	       String[] newtestdata=testData[i][k+1].toString().split(",");
	       adminUsername=newtestdata[0];
	       adminPassword=newtestdata[1];
	       context.setAttribute("username", adminUsername);
	       context.setAttribute("password", adminPassword);
	       break;
	       }
	    }
	        
        test = extent.createTest(method.getName()," Verify User Rights of Member  " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();

            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("Member is able to Login");
		    }else
		    {
		    	 test.fail("Member is not able to login"); 
		    }
            dashBoardpage.clickOnOrder();
            dashBoardpage.clickOnReports();
    }
	//@Test(description = "Verify User Rights of Editor")
    @Parameters({"browserName","webSite"})
    public void smoketest0006(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;	        
        test = extent.createTest(method.getName()," Verify User Rights of Editor  " + " <small><b><i>[" + browser+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();

            DashboardPage dashBoardpage=loginPage.login("editor@editor.com","password".toString(),test);
            iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    if(dashBoardpage.verifyMasterElement()==true)
		    {	    
		    test.pass("Editor is able to Login");
		    }else
		    {
		    	 test.fail("Editor is not able to login"); 
		    }
            
            dashBoardpage.clickOnAssets();
            dashBoardpage.clickOnOrder();
    }

}

