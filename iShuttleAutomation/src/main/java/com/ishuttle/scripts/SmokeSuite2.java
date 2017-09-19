package com.ishuttle.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.remote.Augmenter;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ishuttle.pages.AssetPage;
import com.ishuttle.pages.AssetReportPage;
import com.ishuttle.pages.ClientPage;
import com.ishuttle.pages.CreateOrder;
import com.ishuttle.pages.DashboardPage;
import com.ishuttle.pages.DeliveryReportPage;
import com.ishuttle.pages.LoginPage;
import com.ishuttle.utils.ExtentManager;
import com.ishuttle.utils.ReadCSVData;
import com.ishuttle.utils.ReadExcel;
import com.ishuttle.utils.WebDriverFactory;
import com.ishuttle.utils.iShuttleUtils;
import com.ishuttle.pages.MRPage;
import com.ishuttle.pages.UserReportPage;

public class SmokeSuite2 {
	
	private String webSite;
    private ExtentReports extent;
    public String adminUsername="";
    public String adminPassword="";
    ExtentTest test;
    boolean loginDataFlag=false;
    HashMap<String, String> map= new HashMap<>();
    
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
    
   //@Test(description = "Verify user should be able to Add, Edit MR")
    @Parameters({"browserName","webSite"})
    public void smoketest2001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
	
		// Get the web driver instance
	
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().window().maximize();
		ExtentTest test;
		String clientName="";
		String brandName="";
		String billingClientname="";
		String mRDate="";
		String coOrdinator="";
		String cCEmail="";
		String campaignName="";
		String mediaAgency="";
		String creativeAgency="";
		String remark="";
	    
		test = extent.createTest(method.getName()," Verify user should be able to Add, Edit MR  " + " <small><b><i>[" + browser
				+ "]</b></i></small>"); 
        // Launch Site
        LoginPage loginPage = new LoginPage(driver, webSite,test).get();
        // Entering the login credentials
        
        DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
        dashBoardpage.clickOnMR();
        Object[][] testData=ReadCSVData.readData("MR");
		
	      int k=0;
		    for(int i=0; i<testData.length;i++)
		    {
		    	k=0;
		       if(method.getName().equals(testData[i][k].toString()))
		       {
		       String[] newtestdata=testData[i][k+1].toString().split(",");
		       clientName=newtestdata[0];
		       brandName=newtestdata[1];
		       billingClientname=newtestdata[2];
		       mRDate=newtestdata[3];
		       coOrdinator=newtestdata[4];
		       cCEmail=newtestdata[5];
		       campaignName=newtestdata[6];
		       mediaAgency=newtestdata[7];
		       creativeAgency=newtestdata[8];
		       remark=newtestdata[9];
		       
		     }
		    }
		    MRPage MRPage = dashBoardpage.clickOnMR();
	          MRPage.navAddMR();
	          MRPage.createMR(clientName, brandName , billingClientname , mRDate , coOrdinator , cCEmail , campaignName , mediaAgency , creativeAgency , remark);
	          MRPage.searchClient(clientName);
	          if(MRPage.verifySearchMR(clientName))
	          {
	          	test.log(Status.PASS, "Client is created successfully and displayed in the list");
	          }
	          else
	          {
	          	test.log(Status.FAIL, "Unable to see Client");
	          }
	          
	          //Edit a MR
	          /*MRPage.navEditMR();
	          MRPage.editMR(mRDate, coOrdinator, "ranjit.patil@icastx.com", campaignName, mediaAgency, creativeAgency, remark);
	          MRPage.searchClient(""+clientName);
	          if(MRPage.verifySearchMR(""+clientName))
	          {
	          	test.log(Status.PASS, "MR is created successfully and displayed in the list");
	          }
	          else
	          {
	          	test.log(Status.FAIL, "Unable to see MR");
	          }
	          driver = new Augmenter().augment(driver);
			    File scrFile =
			    ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\AutomationFramework\\iShuttleAutomation\\test-output\\error.png"));
			   */ 
    	}
	
	
	//@Test(description = "Verify user should be able to Add Asset in MR")
    @Parameters({"browserName","webSite"})
	
    public void smoketest2002(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

		// Get the web driver instance
	
		final WebDriver driver = WebDriverFactory.get(browser);
		
		String asset="";
		String channelName="";
		String remark="";
		
		test = extent.createTest(method.getName()," Verify user should be able to Add Asset  " + " <small><b><i>[" + browser
				+ "]</b></i></small>");
        // Launch Site
        LoginPage loginPage = new LoginPage(driver, webSite,test).get();
        // Entering the login credentials
        DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
        dashBoardpage.clickOnMR();
        Object[][] testData=ReadCSVData.readData("Asset");
		
	      int k=0;
		    for(int i=0; i<testData.length;i++)
		    {
		    	k=0;
		       if(method.getName().equals(testData[i][k].toString()))
		       {
		       String[] newtestdata=testData[i][k+1].toString().split(",");
		       asset=newtestdata[0];
		       channelName=newtestdata[1];
		       
		     }
		    }
		    AssetPage AssetPage=MRPage.clickOnAsset();
		    
		// AssetPage AssetPage=MRPage.clickOnAsset();
		    
		    AssetPage.navAddAsset();
		    AssetPage.AddAsset(asset,channelName,remark);
	      AssetPage.searchAsset(asset);
	       
    }

	//@Test(description = "Verify user should be able to Create order")
    @Parameters({"browserName","webSite"})
	
    public void smoketest2003(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

		// Get the web driver instance
	
		WebDriver driver = WebDriverFactory.get(browser);
    
		
    	test = extent.createTest(method.getName()," Verify user should be able to Create Order  " + " <small><b><i>[" + browser
				+ "]</b></i></small>");
        // Launch Site
        LoginPage loginPage = new LoginPage(driver, webSite,test).get();
        
        DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
        dashBoardpage.clickOnMR();
		    CreateOrder CreateOrder=MRPage.clickOnCreateOrder();
		    
		// AssetPage AssetPage=MRPage.clickOnAsset();
		    
		    CreateOrder.AddAsset();
		    
		    //AssetPage.searchAsset(asset);
	
		    driver = new Augmenter().augment(driver);
		    File scrFile =
		    ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File("C:\\Users\\Administrator\\Desktop\\AutomationFramework\\iShuttleAutomation\\test-output\\error1.png"));    
    	}

	//@Test(description = "Verify user should be able to Generate Asset Report")
    @Parameters({"browserName","webSite"})
    public void smoketest2004(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
	
		// Get the web driver instance
	
		WebDriver driver = WebDriverFactory.get(browser);
		
		String client="";
		String brand="";
		String Title="";
		String Language="";
		String AssetNo="";
		test = extent.createTest(method.getName()," Verify user should be able to Generate Asset Report  " + " <small><b><i>[" + browser
				+ "]</b></i></small>");
        // Launch Site
        LoginPage loginPage = new LoginPage(driver, webSite,test).get();
        // Entering the login credentials
          
        DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
        dashBoardpage.clickOnReports();
        //Object[][] testData=ReadCSVData.readData("Asset Report");
        Object[][] testData=ReadExcel.getExcelData("Asset Report");  
		    AssetReportPage AssetReportPage = dashBoardpage.clickOnAssetReport();
		    int k=0;
		    for(int i=0; i<testData.length;i++)
		    {
		    	k=0;
		       if(method.getName().equals(testData[i][k].toString()))
		       {
		       String[] newtestdata=testData[i][k+1].toString().split(",");
		       client=newtestdata[0];
		       brand=newtestdata[1];
		       AssetNo=newtestdata[2];
		       Title=newtestdata[3];
		       Language=newtestdata[4];
		       
		       }
		    }
		    AssetReportPage.createAssetReportPage(client, brand, AssetNo, Title, Language  ); 
    }
		    
	@Test(description = "Verify user should be able to Generate User Report")
    @Parameters({"browserName","webSite"})
    public void smoketest2005(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
     
        String user="";
           
        test = extent.createTest(method.getName()," Verify user should be able to Generate User Report  " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
            dashBoardpage.clickOnReports();
            Object[][] testData=ReadCSVData.readData("User");
            //Object[][] testData=ReadExcel.getExcelData("User");
    		
		      int k=0;
			    for(int i=0; i<testData.length;i++)
			    {
			    	k=0;
			       if(method.getName().equals(testData[i][k].toString()))
			       {
			       String[] newtestdata=testData[i][k+1].toString().split(",");
			       user=newtestdata[0];
			     			     }
			    }
			UserReportPage UserReportPage = dashBoardpage.clickOnUserReport();
			UserReportPage.createUser(user);
            
    }

	//@Test(description = "Verify user should be able to Generate Delivery Report")
    @Parameters({"browserName","webSite"})
    public void smoketest2006(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
      
        String Asset="";
        String channel="";
           
        test = extent.createTest(method.getName()," Verify user should be able to Generate User Report  " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
            dashBoardpage.clickOnReports();
            Object[][] testData=ReadCSVData.readData("Delivery");
            //Object[][] testData=ReadExcel.getExcelData("Delivery");
    		
		      int k=0;
			    for(int i=0; i<testData.length;i++)
			    {
			    	k=0;
			       if(method.getName().equals(testData[i][k].toString()))
			       {
			       String[] newtestdata=testData[i][k+1].toString().split(",");
			       Asset=newtestdata[0];
			       channel=newtestdata[1];
			     			     }
			    }
			DeliveryReportPage DeliveryReportPage = dashBoardpage.clickOnDeliveryReport();
			DeliveryReportPage.generateDeliveryReport(Asset, channel);
           }
    
    @AfterMethod
    public void getResult(ITestResult result)
    {
	 
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }


@AfterClass
public void tear()
{
	//extent.endTest(test);//earlier version
	extent.flush();
	
}
    
}

