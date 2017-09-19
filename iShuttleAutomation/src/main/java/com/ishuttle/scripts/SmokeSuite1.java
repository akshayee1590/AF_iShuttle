package com.ishuttle.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
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

	public class SmokeSuite1 {
    private String webSite;
    private ExtentReports extent;
    ExtentTest test;
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

  // @Test(description = "Verify the functionality when user Login")
    @Parameters({"browserName","webSite"})
    public void login001(String browser,String webSite,ITestContext context,Method method,ITestResult result) throws Exception {
      	   
        // Get the web driver instance
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        Object[][] testData=ReadCSVData.readData("Login");

        //Object[][] testData=ReadExcel.getExcelData("Login");
        
      
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
		    test = extent.createTest(method.getName(), "Verify User is able to Login on Entering valid credentials<small><b><i>[" + browser+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            // Entering the login credentials
		    DashboardPage dbPage=loginPage.login("admin@admin.com","password".toString(),test);
		  
		    if(dbPage.verifyMasterElement()==true)
		    {	    
		    	test.pass("User is able to Login");
		    }else
		    {
		    	 test.fail("User is not able to login"); 
		    	 iShuttleUtils.captureAndDisplayScreenShot(driver, test);
		    	 test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
		    }
		  
		    
        }
  
		  public void getLoginData(String method,ITestContext context)
		  {
			  if(loginDataFlag==false)
			  {
			  
			    Object[][] testData=ReadCSVData.readData("Login");
		
		      int k=0;
			    for(int i=0; i<testData.length;i++)
			    {
			    	k=0;
			       if(method.equals(testData[i][k].toString()))
			       {
			       String[] newtestdata=testData[i][k+1].toString().split(",");
			       adminUsername=newtestdata[0];
			       adminPassword=newtestdata[1];
			       map.put(adminUsername, adminPassword);
			       break;
			     }
		      
		      }
			  }
			  loginDataFlag=true;
		  }

	
	@Test(description = "Verify user should be able to Add, Edit and Delete Client")
    @Parameters({"browserName","webSite"})
    public void smoketest0001(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {

        // Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
    
        String clientName="";
        String clientCode="";
        getLoginData(method.getName(),context);
	        
        test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Client  " + " <small><b><i>[" + browser
					+ "]</b></i></small>");
            // Launch Site
        LoginPage loginPage = new LoginPage(driver, webSite,test).get();
        DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
            
            // Entering the login credentials
            Iterator<?> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                adminUsername=pair.getKey().toString();
                adminPassword=pair.getValue().toString();
         
                it.remove(); // avoids a ConcurrentModificationException
            }
            
            dashBoardpage.clickOnMaster(); 
            Object[][] testData=ReadCSVData.readData("Client");  		

//          Object[][] testData=ReadExcel.getExcelData("Client");
            int k=0;
			    for(int i=0; i<testData.length;i++)
			    {
			    	k=0;
			       if(method.getName().equals(testData[i][k].toString()))
			       {
			       String[] newtestdata=testData[i][k+1].toString().split(",");
			       clientName=newtestdata[0];
			       clientCode=newtestdata[1];
			     }
			    }
            ClientPage clientPage = dashBoardpage.clickOnClient();
            clientPage.navAddClient();
            clientPage.createClient(clientName, clientCode,"Producer","Cl4", "akshayee.sawant@icastx.com", "clremark");
            dashBoardpage.clickOnClient();
            clientPage.searchClient(clientName);
            if(clientPage.verifySearchClient(clientName))
            {
            	test.log(Status.PASS, "Client is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Client");
            }
            
            //Edit a Client
           /*  clientPage.navEditClient();
            clientPage.editClient(clientName, clientCode,"End Client","Cl3", "ranjit.patil@icastx.com", "clremark");
            clientPage.searchClient(""+clientName);
            if(clientPage.verifySearchClient(""+clientName))
            {
            	test.log(Status.PASS, "Client is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Client");
            }
           
           //Delete a Client
           /* clientPage.deleteClient(true);
            dashBoardpage.clickOnClient();
            clientPage.searchClient("Edit"+clientName);
            if(!clientPage.verifySearchClient("Edit"+clientName))
            {
            	test.log(Status.PASS, "Client is Deleted successfully");
            }
            else
            {
            	test.log(Status.FAIL, "Client not deleted successfully");
            }
           
            dashBoardpage.clickOnMaster();
            dashBoardpage.clickOnActivityLog();
           // String bodyText = driver.findElement(By.tagName("8")).getText();
            //Assert.assertTrue("Text not found!", bodyText.contains(text));
            if(driver.getPageSource().contains("8"))
            {
          	     test.log(Status.PASS, "Honda is created successfully and updated in Activity Log");
                          }

            else
            {
          	  test.log(Status.FAIL, "Action was not updated in Activity Log");
            }

    */
    	}
			    
   
 //   @Test(description = "Verify user should be able to Add, Edit and Delete Brand")
    @Parameters({"browserName","webSite"})
    public void smoketest0002(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
 	// Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
       
        String clientName="";	
        String brandName="";
        getLoginData(method.getName(),context);
 	        
        test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Brand  " + " <small><b><i>[" + browser
 					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            // Entering the login credentials
            Iterator<?> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                adminUsername=pair.getKey().toString();
                adminPassword=pair.getValue().toString();
         
                it.remove(); // avoids a ConcurrentModificationException
            }
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            dashBoardpage.clickOnMaster();
            Object[][] testData=ReadCSVData.readData("Brand");
          
 		      
            int k=0;
 			    for(int i=0; i<testData.length;i++)
 			    {
 			    	k=0;
 			       if(method.getName().equals(testData[i][k].toString()))
 			       {
 			       String[] newtestdata=testData[i][k+1].toString().split(",");
 			       clientName=newtestdata[0];
 			       brandName=newtestdata[1];
 			       
 			     }
 			    }
 			    
 			   
            BrandPage brandPage = dashBoardpage.clickOnBrand();
            brandPage.navAddBrand();
            brandPage.createBrand(clientName, brandName,"1");
            dashBoardpage.clickOnBrand();
            brandPage.searchBrand(brandName);
            if(brandPage.verifySearchBrand(brandName))
            {
            	test.log(Status.PASS, "Brand is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Brand");
            }
            
            //Edit a Brand
            brandPage.navEditBrand();
            brandPage.editBrand("Fanta", "1");
            brandPage.searchBrand(""+clientName);
            if(brandPage.verifySearchBrand(""+clientName))
            {
            	test.log(Status.PASS, "Brand is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Brand");
            }
           
           //Delete a Brand
           /* brandPage.deleteBrand(true);
            dashBoardpage.clickOnBrand();
            brandPage.searchBrand("Edit"+brandName);
            if(!brandPage.verifySearchClient("Edit"+brandName))
            {
            	test.log(Status.PASS, "Brand is Deleted successfully");
            }
            else
            {
            	test.log(Status.FAIL, "Brand not deleted successfully");
            }*/
           }
			    
    //@Test(description = "Verify user should be able to Add, Edit and Delete Branch")
    @Parameters({"browserName","webSite"})
    public void smoketest0003(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
 	// Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        ExtentTest test;
        String clientName="";	
        String branchName="";
        String branchAddress="";
        String branchCity="";
        String branchEmail="";
        String branchPhone="";
        String branchRemarks="";
        getLoginData(method.getName(),context);
 	        
        test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Branch  " + " <small><b><i>[" + browser
 					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            // Entering the login credentials
            Iterator<?> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                adminUsername=pair.getKey().toString();
                adminPassword=pair.getValue().toString();
         
                it.remove(); // avoids a ConcurrentModificationException
            }
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            dashBoardpage.clickOnMaster();
            Object[][] testData=ReadCSVData.readData("Branch");
          
 		      
            int k=0;
 			    for(int i=0; i<testData.length;i++)
 			    {
 			    	k=0;
 			       if(method.getName().equals(testData[i][k].toString()))
 			       {
 			       String[] newtestdata=testData[i][k+1].toString().split(",");
 			       clientName=newtestdata[0];
 			       branchName=newtestdata[1];
 			       branchAddress=newtestdata[2];
 			      branchCity=newtestdata[3];
 			      branchEmail=newtestdata[4];
 			      branchPhone=newtestdata[5];
 			      branchRemarks=newtestdata[6];
 			     }
 			    }
 			    
 			   
            BranchPage branchPage = dashBoardpage.clickOnBranch();
            branchPage.navAddBranch();
            branchPage.createBranch(clientName, branchName, branchAddress, branchCity, branchEmail, branchPhone, branchRemarks);
            dashBoardpage.clickOnBranch();
            branchPage.searchBranch(branchName);
           if(branchPage.verifySearchBranch(branchName))
           {
        	   test.log(Status.PASS, "Branch is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Branch");
            }
            
            //Edit a Branch
            branchPage.navEditBranch();
            branchPage.editBranch("Bharatinagar", "Kothrud1","Pune1","ranjit7001@gmail.com", "9850977001", "");
            branchPage.searchBranch(""+clientName);
            if(branchPage.verifySearchBranch(""+clientName))
            {
            	test.log(Status.PASS, "Branch is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Branch");
            }
           
           //Delete a Branch
           /* branchPage.deleteBranch(true);
            dashBoardpage.clickOnBranch();
            branchPage.searchBranch("Edit"+BranchName);
            if(!branchPage.verifySearchClient("Edit"+BranchName))
            {
            	test.log(Status.PASS, "Branch is Deleted successfully");
            }
            else
            {
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

<suite name="iShuttle Test Automation" verbose="3"  parallel="false" thread-count="1" >
	<parameter name="deviceHost" value="localhost" />
	<parameter name="devicePort" value="4444" />
		<parameter name="browserName" 
            	test.log(Status.FAIL, "Branch not deleted successfully");
            }*/
           }

    //@Test(description = "Verify user should be able to Add, Edit and Delete Contact")
    @Parameters({"browserName","webSite"})
    public void smoketest0004(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
 	// Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        
        String clientName="";	
        String branchName="";
        String contactName="";
        String contactphone="";
        String contactEmail="";
        String contactMobile="";
        String contactRemarks="";
        getLoginData(method.getName(),context);
 	        
        test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Contact  " + " <small><b><i>[" + browser
 					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            // Entering the login credentials
            Iterator<?> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                adminUsername=pair.getKey().toString();
                adminPassword=pair.getValue().toString();
         
                it.remove(); // avoids a ConcurrentModificationException
            }
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            dashBoardpage.clickOnMaster();
            Object[][] testData=ReadCSVData.readData("Contact");
          
 		      
            int k=0;
 			    for(int i=0; i<testData.length;i++)
 			    {
 			    	k=0;
 			       if(method.getName().equals(testData[i][k].toString()))
 			       {
 			       String[] newtestdata=testData[i][k+1].toString().split(",");
 			       			clientName= newtestdata[0];	
 			       			branchName= newtestdata[1];
 			       			contactName= newtestdata[2];
 			       			contactphone= newtestdata[3];
 			       			contactEmail= newtestdata[4];
 			       			contactMobile= newtestdata[5];;
 			       			contactRemarks= newtestdata[6]; 			    
 			   
 			       }
 			    }

            ContactPage contactPage = dashBoardpage.clickOnContact();
            contactPage.navAddContact();
            contactPage.createContact(clientName, branchName, contactName, contactphone, contactEmail, contactMobile, contactRemarks);
            dashBoardpage.clickOnContact();
            contactPage.searchContact(contactName);
           if(contactPage.verifySearchContact(contactName))
           {
        	   test.log(Status.PASS, "Contact is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Contact");
            }
            
            //Edit a Contact
            contactPage.navEditContact();
            contactPage.editContact("Ranjit", "02025281990", "ranjit7001@gmail.com", "9850977001", "123");
            contactPage.searchContact(""+contactName);
            if(contactPage.verifySearchContact(""+contactName))
            {
            	test.log(Status.PASS, "Contact is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see Contact");
            }
           
           //Delete a Contact
           /* contactPage.deleteContact(true);
            dashBoardpage.clickOnContact();
            contactPage.searchContact("Edit"+ContactName);
            if(!contactPage.verifySearchClient("Edit"+ContactName))
            {
            	test.log(Status.PASS, "Contact is Deleted successfully");
            }
            else
            {
            	test.log(Status.FAIL, "Contact not deleted successfully");
            }*/
           }
    
    //@Test(description = "Verify user should be able to Add, Edit and Delete ChannelHouse")
    @Parameters({"browserName","webSite"})
    public void smoketest0005(String browser,String webSite,ITestContext context,Method method) throws Exception 
    {
 	// Get the web driver instance
    	
        final WebDriver driver = WebDriverFactory.get(browser);
        
        String channelHouseName="";	
        String channelhouseTypeName="";
        String channelHouseIp= "";
        String channelHouseLocation= "";
        String channelHouseProcess= "";
        String channelHouseftplink= "";
        String channelHouseftpUser= "";
        String channelHouseftppassword= "";
        String channelHouseRemarks="";		    
        getLoginData(method.getName(),context);
 	        
        test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete ChannelHouse  " + " <small><b><i>[" + browser
 					+ "]</b></i></small>");
            // Launch Site
            LoginPage loginPage = new LoginPage(driver, webSite,test).get();
            // Entering the login credentials
            Iterator<?> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                adminUsername=pair.getKey().toString();
                adminPassword=pair.getValue().toString();
         
                it.remove(); // avoids a ConcurrentModificationException
            }
            DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
            dashBoardpage.clickOnMaster();
            //Object[][] testData=ReadCSVData.readData("ChannelHouse");
          
            Object[][] testData=ReadExcel.getExcelData("ChannelHouse");
 
            int k=0;
 			    for(int i=0; i<testData.length;i++)
 			    {
 			    	k=0;
 			       if(method.getName().equals(testData[i][k].toString()))
 			       {
 			       String[] newtestdata=testData[i][k+1].toString().split(",");
 			       			channelHouseName= newtestdata[0];	
 			       			channelhouseTypeName= newtestdata[1];
 			       			channelHouseIp= newtestdata[2];
 			       			channelHouseLocation= newtestdata[3];
 			       			channelHouseProcess= newtestdata[4];
 			       			channelHouseftplink= newtestdata[5];
 			       			channelHouseftpUser= newtestdata[6];
 			       			channelHouseftppassword= newtestdata[7];
 			       			channelHouseRemarks= newtestdata[8]; 			    
 			   
 			       }
 			    }

           ChannelHousePage channelHousePage = dashBoardpage.clickOnChannelHouse();
           channelHousePage.navAdd();
           channelHousePage.createChannelHouse(channelHouseName, channelhouseTypeName, channelHouseIp, channelHouseLocation, 
            										channelHouseProcess, channelHouseftplink, channelHouseftpUser, channelHouseftppassword, channelHouseRemarks);
           dashBoardpage.clickOnChannelHouse();
           channelHousePage.searchChannelHouse(channelHouseName);
           if(channelHousePage.verifySearchChannelHouse(channelHouseName))
           {
        	   test.log(Status.PASS, "ChannelHouse is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see ChannelHouse");
            }
            
            //Edit a ChannelHouse
            channelHousePage.navEdit();
            channelHousePage.editChannelHouse("10.24.126.25", "Chennai","Etv@Etv","123", "Ranjit", "mind");
            channelHousePage.searchChannelHouse(""+channelHouseName);
            if(channelHousePage.verifySearchChannelHouse(""+channelHouseName))
            {
            	test.log(Status.PASS, "ChannelHouse is created successfully and displayed in the list");
            }
            else
            {
            	test.log(Status.FAIL, "Unable to see ChannelHouse");
            }
           
           //Delete a ChannelHouse
           /* channelHousePage.deleteChannelHouse(true);
            dashBoardpage.clickOnChannelHouse();
            channelHousePage.searchChannelHouse("Edit"+ChannelHouseName);
            if(!channelHousePage.verifySearchClient("Edit"+ChannelHouseName))
            {
            	test.log(Status.PASS, "ChannelHouse is Deleted successfully");
            }
            else
            {
            	test.log(Status.FAIL, "ChannelHouse not deleted successfully");
            }*/
    }
    
    
      //@Test (description = "Verify user should be able to Add, Edit and Delete Channel")
      @Parameters({"browserName","webSite"})
       public void smoketest0006(String browser,String webSite,ITestContext context,Method method) throws Exception 
            {
         	// Get the web driver instance
            	
                final WebDriver driver = WebDriverFactory.get(browser);
             
               String channelName="";
               String channelType="";
               String channelHouse="";
               String channelLanguages="";
               String channelNotifyTo="";
               String channelRemarks="";
                getLoginData(method.getName(),context);
         	        
                test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Channel  " + " <small><b><i>[" + browser
         					+ "]</b></i></small>");
                    // Launch Site
                    LoginPage loginPage = new LoginPage(driver, webSite,test).get();
                    // Entering the login credentials
                    Iterator<?> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        adminUsername=pair.getKey().toString();
                        adminPassword=pair.getValue().toString();
                 
                        it.remove(); // avoids a ConcurrentModificationException
                    }
                    DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
                    dashBoardpage.clickOnMaster();
                    Object[][] testData=ReadCSVData.readData("Channel");
                  
         		      
                    int k=0;
         			    for(int i=0; i<testData.length;i++)
         			    {
         			    	k=0;
         			       if(method.getName().equals(testData[i][k].toString()))
         			       {
         			       String[] newtestdata=testData[i][k+1].toString().split(",");
         			       			channelName= newtestdata[0];	
         			       			channelType= newtestdata[1];
         			       			channelHouse= newtestdata[2];
         			       			channelLanguages= newtestdata[3];
         			       			channelNotifyTo= newtestdata[4];
         			       			channelRemarks= newtestdata[5]; 
         			       }
         			    }

                    ChannelPage channelPage = dashBoardpage.clickOnChannel();
                   channelPage.navAdd();
                    channelPage.createChannel(channelName,channelType, channelHouse,channelLanguages,channelNotifyTo,channelRemarks);
                    dashBoardpage.clickOnChannel();
                    channelPage.searchChannel(channelName);
                   if(channelPage.verifySearchChannel(channelName))
                   {
                	   test.log(Status.PASS, "Channel is created successfully and displayed in the list");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Unable to see Channel");
                    }
                    
                    //Edit a Channel
                   	channelPage.navEditChannel();
                    channelPage.editChannel("Etv Bangla", "Small", "ranjit7001@gmail.com");
                    channelPage.searchChannel(""+channelHouse);
                    if(channelPage.verifySearchChannel(""+channelHouse))
                    {
                    	test.log(Status.PASS, "Channel is created successfully and displayed in the list");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Unable to see Channel");
                    }
                   /*
                   //Delete a Channel
                   channelPage.deleteChannel(true);
                    dashBoardpage.clickOnChannel();
                    channelPage.searchChannel("Edit"+ChannelName);
                    if(!channelPage.verifySearchClient("Edit"+ChannelName))
                    {
                    	test.log(Status.PASS, "Channel is Deleted successfully");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Channel not deleted successfully");
                    }
                   }*/

            }
    //@Test(description = "Verify user should be able to Add, Edit and Delete Language")
      @Parameters({"browserName","webSite"})
            public void smoketest0007(String browser,String webSite,ITestContext context,Method method) throws Exception 
            {
         	// Get the web driver instance
            	
                final WebDriver driver = WebDriverFactory.get(browser);
                
                String languageName="";
                String languageRemarks="";
               
                getLoginData(method.getName(),context);
         	        
                test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete Language  " + " <small><b><i>[" + browser
         					+ "]</b></i></small>");
                    // Launch Site
                    LoginPage loginPage = new LoginPage(driver, webSite,test).get();
                    // Entering the login credentials
                    Iterator<?> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        adminUsername=pair.getKey().toString();
                        adminPassword=pair.getValue().toString();
                 
                        it.remove(); // avoids a ConcurrentModificationException
                    }
                    DashboardPage dashBoardpage=loginPage.login(adminUsername,adminPassword.toString(),test);
                    dashBoardpage.clickOnMaster();
                    Object[][] testData=ReadCSVData.readData("Language");
                  
         		      
                    int k=0;
         			    for(int i=0; i<testData.length;i++)
         			    {
         			    	k=0;
         			       if(method.getName().equals(testData[i][k].toString()))
         			       {
         			       String[] newtestdata=testData[i][k+1].toString().split(",");
         			       			languageName= newtestdata[0];	
         			       			languageRemarks= newtestdata[1]; 
         			       }
         			    }

                    LanguagePage languagePage = dashBoardpage.clickOnLanguage();
                   languagePage.navAdd();
                    languagePage.createLanguage(languageName ,languageRemarks);
                    dashBoardpage.clickOnLanguage();
                    languagePage.searchLanguage(languageName);
                   if(languagePage.verifySearchLanguage(languageName))
                   {
                	   test.log(Status.PASS, "Language is created successfully and displayed in the list");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Unable to see Language");
                    }
                    
                    //Edit a Language
                    languagePage.navEdit();
                    languagePage.editLanguage(languageName, "12345 ");
                    languagePage.searchLanguage(""+languageName);
                    if(languagePage.verifySearchLanguage(""+languageName))
                    {
                    	test.log(Status.PASS, "Language is created successfully and displayed in the list");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Unable to see Language");
                    }
                   
                   //Delete a Language
                    /*languagePage.deleteLanguage(true);
                    dashBoardpage.clickOnLanguage();
                    languagePage.searchLanguage("Edit"+LanguageName);
                    if(!languagePage.verifySearchClient("Edit"+LanguageName))
                    {
                    	test.log(Status.PASS, "Language is Deleted successfully");
                    }
                    else
                    {
                    	test.log(Status.FAIL, "Language not deleted successfully");
                    }*/
                   }

      
 //  @Test(description = "Verify user should be able to Add, Edit and Delete User")
      @Parameters({"browserName","webSite"})
      public void smoketest0008(String browser,String webSite,ITestContext context,Method method) throws Exception 
      {
   	// Get the web driver instance
      	
          final WebDriver driver = WebDriverFactory.get(browser);
        
  		
  		 String user_name="";
  		 String first_name="";
  		 String last_name="";
  		 String User_email="";
  		 String User_phone="";
  		 String entityid="";
  		 String UserType="";
  		 String pwd="";
  		 String confirmPwd="";
  		 String old_Pwd="";
  		 String new_Pwd="";
  		 test = extent.createTest(method.getName()," Verify user should be able to Add, Edit and Delete User  " + " <small><b><i>[" + browser
   					+ "]</b></i></small>");
              // Launch Site
              LoginPage loginPage = new LoginPage(driver, webSite,test).get();
              DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
              dashBoardpage.clickOnIonAuth();
             // Object[][] testData=ReadExcel.getExcelData("Users");            
             Object[][] testData=ReadCSVData.readData("Users");
              
              int k=0;
   			    for(int i=0; i<testData.length;i++)
   			    {
   			    	k=0;
   			       if(method.getName().equals(testData[i][k].toString()))
   			       {
   			       String[] newtestdata=testData[i][k+1].toString().split(",");
   				   user_name=newtestdata[0];
   		  		   first_name=newtestdata[1];
   		  		   last_name=newtestdata[2];
   		  		   User_email=newtestdata[3];
   		  		   User_phone=newtestdata[4];
   		  		   entityid=newtestdata[5];
   		  		   UserType=newtestdata[6];
   		  		   pwd=newtestdata[7];
   		  		   confirmPwd=newtestdata[8];
  			     }
   			    }
   			       			   
              UsersPage UsersPage = dashBoardpage.clickOnUsers();
              UsersPage.navAddUser();
              UsersPage.createUser(user_name, first_name, last_name, User_email, User_phone, entityid, UserType, pwd, confirmPwd);
              dashBoardpage.clickOnIonAuth();
              dashBoardpage.clickOnUsers();
              UsersPage.searchUser(user_name);
              if(UsersPage.verifySearchUser(user_name))
              {
              	test.log(Status.PASS, "User is created successfully and displayed in the list");
              }
              else
              {
              	test.log(Status.FAIL, "Unable to see User");
              }
              /*        
              //Edit a User
              dashBoardpage.clickOnIonAuth();
              UsersPage.navEditUser();
              UsersPage.editUser(user_name, first_name, "a", User_email, User_phone, entityid, UserType, "password", "password");
              UsersPage.searchUser(""+user_name);
              if(UsersPage.verifySearchUser(""+user_name))
              {
              	test.log(Status.PASS, "User is created successfully and displayed in the list");
              }
              else
              {
              	test.log(Status.FAIL, "Unable to see User");
              }
             
             //Delete a Brand
              dashBoardpage.clickOnIonAuth();
              UsersPage.deleteUser(true);
              dashBoardpage.clickOnUsers();
              UsersPage.searchUser(""+user_name);
              if(!UsersPage.verifySearchUser(""+user_name))
              {
              	test.log(Status.PASS, "User is Deleted successfully");
              }
              else
              {
              	test.log(Status.FAIL, "User not deleted successfully");
              }
      */	   
      }
      	
   //@Test(description = "Verify that client add activity reflecting in Activity log or not")
        @Parameters({"browserName","webSite"})
        public void smoketest0009(String browser,String webSite,ITestContext context,Method method) throws Exception 
              {

                  final WebDriver driver = WebDriverFactory.get(browser);
                  
                   
                  test = extent.createTest(method.getName()," Verify that client add activity reflecting in Activity log or not  "
                		  			+ " <small><b><i>[" + browser + "]</b></i></small>");
                      // Launch Site
                      LoginPage loginPage = new LoginPage(driver, webSite,test).get();
                      DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
                     /* dashBoardpage.clickOnMaster(); 
                      dashBoardpage.clickOnClient();
                      ClientPage clientPage = dashBoardpage.clickOnClient();
                      clientPage.navAddClient();
                      clientPage.createClient("Honda", "Honda","Producer","Ranjit", "ranjit.patil@icastx.com", "clremark");
                      dashBoardpage.clickOnClient();
                      clientPage.searchClient("Honda");
                      if(clientPage.verifySearchClient("Honda"))
                      {
                      	test.log(Status.PASS, "Honda is created successfully and displayed in the list");
                      }
                      else
                      {
                      	test.log(Status.FAIL, "Unable to see Honda");
                      }
                      dashBoardpage.clickOnMaster();
                      dashBoardpage.clickOnActivityLog();
                     // String bodyText = driver.findElement(By.tagName("8")).getText();
                      //Assert.assertTrue("Text not found!", bodyText.contains(text));
                      if(driver.getPageSource().contains("8"))
                      {
                    	 
                           test.log(Status.PASS, "Honda is created successfully and updated in Activity Log");
                         
                      }

                      else
                      {
                    	  test.log(Status.FAIL, "Action was not updated in Activity Log");
                      }*/
                      dashBoardpage.clickOnMaster();
                      ClientPage clientPage = dashBoardpage.clickOnClient();
                      clientPage.UCClient01();
              }
      
    // @Test(description = "Verify that correct error shown when User enter wrong email")
        @Parameters({"browserName","webSite"})
        public void smoketest0010(String browser,String webSite,ITestContext context,Method method) throws Exception
        {
      		final WebDriver driver = WebDriverFactory.get(browser);
            
             
            test = extent.createTest(method.getName()," Verify that correct error shown when User enter wrong email "
          		  			+ " <small><b><i>[" + browser + "]</b></i></small>");
                // Launch Site
                LoginPage loginPage = new LoginPage(driver, webSite,test).get();
                DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
              dashBoardpage.clickOnMaster();
              ClientPage clientPage = dashBoardpage.clickOnClient();
              clientPage.navAddClient();
              clientPage.createClient("One Plus", "1+", "End Client", "OnePlus Manager", "1+", "1+5");
              clientPage.WrongMail();
        }
            
       // @Test(description = "Verify that correct error shown when User enter Duplicate Client code")
        @Parameters({"browserName","webSite"})
        public void smoketest0011(String browser,String webSite,ITestContext context,Method method) throws Exception
        {
      		final WebDriver driver = WebDriverFactory.get(browser);
            
             
            test = extent.createTest(method.getName()," Verify that correct error shown when User enter wrong email "
          		  			+ " <small><b><i>[" + browser + "]</b></i></small>");
                // Launch Site
                LoginPage loginPage = new LoginPage(driver, webSite,test).get();
                DashboardPage dashBoardpage=loginPage.login("admin@admin.com","password".toString(),test);
              dashBoardpage.clickOnMaster();
              ClientPage clientPage = dashBoardpage.clickOnClient();
              clientPage.navAddClient();
              clientPage.createClient("One Plus", "OnePLus", "End Client", "OnePlus Manager", "Manager@OnePlus.com", "1+5");
              clientPage.DuplicateClientCode();
        }
        
        
        @Test
        public void demoTestPass()
        {
            test = extent.createTest("demoTestPass", "This test will demonstrate the PASS test case");
            Assert.assertTrue(true);
        }
         
        @Test
        public void demoTestFail()
        {
            test = extent.createTest("demoTestFail", "This test will demonstrate the FAIL test case");
            Assert.assertTrue(false);
        }
         
        @Test
        public void demoTestSkip()
        {
            test = extent.createTest("demoTestSkip", "This test will demonstrate the SKIP test case");
            throw new SkipException("This test case not ready for execution");
        }
        
        /*
    	@Test
    	public void checkFail(){
    		test = extent.createTest("Testing how fail works");
    		//test.log(Status.INFO, "fail check started");
    		test.fail("Test fail");
    	}
    	*/
    	
       
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
