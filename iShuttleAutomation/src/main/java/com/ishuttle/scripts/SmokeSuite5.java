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

public class SmokeSuite5 {
	
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
    
    
	//@Test(description = "Verify user should be able to Generate Delivery Report")
    @Parameters({"browserName","webSite"})
    public void smoketest2(String browser,String webSite,ITestContext context,Method method) throws Exception 
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
