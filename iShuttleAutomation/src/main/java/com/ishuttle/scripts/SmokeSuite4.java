package com.ishuttle.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ishuttle.pages.BranchPage;
import com.ishuttle.pages.BrandPage;
import com.ishuttle.pages.ChannelHousePage;
import com.ishuttle.pages.ChannelPage;
import com.ishuttle.pages.ClientPage;
import com.ishuttle.pages.ContactPage;
import com.ishuttle.pages.DashboardPage;
import com.ishuttle.pages.LanguagePage;
import com.ishuttle.pages.LoginPage;
import com.ishuttle.pages.UsersPage;
import com.ishuttle.utils.ExtentManager;
import com.ishuttle.utils.ReadCSVData;
import com.ishuttle.utils.ReadExcel;
import com.ishuttle.utils.WebDriverFactory;
import com.ishuttle.utils.iShuttleUtils;

	public class SmokeSuite4 {
    private String webSite;
    private ExtentReports extent;
    public String adminUsername="";
    public String adminPassword="";
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

    @Test(description = "Verify User Rights for Admin")
    @Parameters({"browserName","webSite"})
    public void login00123(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	
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
    	       
		List<WebElement> liElements = driver.findElements(By
	                .xpath(".//*[@id='menuSub']/li[6]/ul/li"));
		String[] permission= {"Client","Brand","Branch","Contact","Channel","Language","Category","Sub Category"};
	    System.out.println(liElements.size());
	    System.out.println(" ");
	    System.out.println("_________________________________________________________");
	    System.out.println(" ");

	        
	    for (int i = 1; i < liElements.size()+1;)
	    {
	    	WebElement linkElement = driver
	                    .findElement(By
	                            .xpath(".//*[@id='menuSub']/li[6]/ul/li[" + i
	                                    + "]/a"));
	    	String page=linkElement.getText();
	        System.out.println(page);
	        System.out.println(permission[i-1]);
	        		
	        if(page.equals(permission[i-1]))
	        {	
	        			
	        	linkElement.click();
	        	i++;
	        }
	        else
	        {
	        	System.out.println(" ");
	        	System.out.println("---------------------------------------------------------");
	        	Thread.sleep(2000);
	        	System.err.println("Admin does not have permission to view "+ page);
	        	Thread.sleep(2000);
	        	System.out.println("----------------------------------------------------------");
	        	System.out.println(" ");
	        	break;
	        }  	  	
	    }
	        
	    dashBoardpage.clickOnReports();
	        
	    List<WebElement> liReports = driver.findElements(By
	                .xpath(".//*[@id='menuSub']/li[7]/a"));
	    String[] Reports= {"Asset Report","User Report","Delivery Report"};
	    System.out.println(liReports.size());
	    System.out.println(" ");
	    System.out.println("_________________________________________________________");
	    System.out.println(" ");
	    for (int i = 1; i < liReports.size()+1;)
	    {
	    	WebElement linkReports = driver
	                    .findElement(By
	                            .xpath(".//*[@id='menuSub']/li[7]/ul/li[" + i
	                                    + "]/a"));
	        String Reportpage=linkReports.getText();
	        System.out.println(Reportpage);
	        System.out.println(Reports[i-1]);
        	if(Reportpage.equals(Reports[i-1]))
        	{	
        		linkReports.click();
        		i++;
        			
        	}
        	else
        	{
        		System.out.println(" ");
        		System.out.println("---------------------------------------------------------");
        		Thread.sleep(2000);
        		System.err.println("Admin does not have permission to view "+ Reportpage);
        		Thread.sleep(2000);
        		System.out.println("---------------------------------------------------------");
        		System.out.println(" ");
        		break;
        	}
        	
	    }	
            
    	System.out.println("*******----------------************----------------*******");
    }
    

    @Test(description = "Verify Order count for Admin")
    @Parameters({"browserName","webSite"})
    public void order0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify orders  " + " <small><b><i>[" + browser
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
		    
        String TotalOrder=driver.findElement(By.xpath("html/body/div[1]/div/section[2]/section/div[1]/div[1]/div/div/span[2]")).getText();
		System.out.println("Order: "+ TotalOrder);
    	 
		driver.findElement(By.linkText("Order")).click();
		    
		String entries=driver.findElement(By.xpath(".//*[@id='flex1_info']")).getText();
		    
		String[] parts = entries.split(" ");
		    
		String order = parts[5];
		    
		System.out.println("Total Oders: "+order);
		    
		Assert.assertEquals(TotalOrder,order);
		    
		System.out.println("*****************************************");
		    
    }
    
    
    //@Test(description = "Verify Asset count for Admin")
    @Parameters({"browserName","webSite"})
    public void asset0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify number of Assets  " + " <small><b><i>[" + browser
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
		    
        String TotalAsset=driver.findElement(By.xpath("html/body/div[1]/div/section[2]/section/div[1]/div[2]/div/div/span[2]")).getText();
        System.out.println("Asset: "+ TotalAsset);
    	driver.findElement(By.linkText("Assets")).click();
		String entries=driver.findElement(By.xpath(".//*[@id='flex1_info']")).getText();
		String[] parts = entries.split(" ");
		String Asset = parts[5];
		System.out.println("Total Oders: "+Asset);
		Assert.assertEquals(TotalAsset,Asset);
		System.out.println("*****************************************");
		    
    }
    
    
    //@Test(description = "Verify Users count for Admin")
    @Parameters({"browserName","webSite"})
    public void users0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify number of Users  " + " <small><b><i>[" + browser
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
		    
		String TotalUsers=driver.findElement(By.xpath("html/body/div[1]/div/section[2]/section/div[1]/div[4]/div/div/span[2]")).getText();
		System.out.println("Users: "+ TotalUsers);
    	 
		driver.findElement(By.linkText("Users")).click();
		    
		String entries=driver.findElement(By.xpath(".//*[@id='flex1_info']")).getText();
		    
		String[] parts = entries.split(" ");
		    
		String User = parts[5];
		    
		System.out.println("Total No. of Users: "+User);
		    
		Assert.assertEquals(TotalUsers,User);
		    
		System.out.println("*****************************************");
		    
    }
    
    
    //@Test(description = "Verify Channel count for Admin")
    @Parameters({"browserName","webSite"})
    public void channel0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify number of Channel  " + " <small><b><i>[" + browser
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
		    
		String TotalChannel=driver.findElement(By.xpath("html/body/div[1]/div/section[2]/section/div[2]/div[5]/div/div/span[2]")).getText();
		System.out.println("Channel: "+ TotalChannel);
    	 
		driver.findElement(By.linkText("Master")).click();

		driver.findElement(By.linkText("Channel")).click();
		    
		String entries=driver.findElement(By.xpath(".//*[@id='flex1_info']")).getText();
		    
		String[] parts = entries.split(" ");
		    
		String Channel = parts[5];
		    
		System.out.println("Total No. of Clients: "+Channel);
		    
		Assert.assertEquals(TotalChannel,Channel);
		    
		System.out.println("*****************************************");
		    
    }
    
    //@Test(description = "Verify Clients count for Admin")
    @Parameters({"browserName","webSite"})
    public void client0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
    	final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        test = extent.createTest(method.getName()," Verify number of Clients  " + " <small><b><i>[" + browser
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
		    
		String TotalClients=driver.findElement(By.xpath("html/body/div[1]/div/section[2]/section/div[2]/div[1]/div/div/span[2]")).getText();
		System.out.println("Clients: "+ TotalClients);
    	 
		driver.findElement(By.linkText("Master")).click();

		driver.findElement(By.linkText("Client")).click();
		    
		String entries=driver.findElement(By.xpath(".//*[@id='flex1_info']")).getText();
		    
		String[] parts = entries.split(" ");
		    
		String Client = parts[5];
		    
		System.out.println("Total No. of Clients: "+Client);
		    
		Assert.assertEquals(TotalClients,Client);
		    
		System.out.println("*****************************************");
		    
    }
    
    
    
    @AfterClass
    public void tear()
    {
    	//extent.endTest(test);//earlier version
    	extent.flush();
    }
}
