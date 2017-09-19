package com.ishuttle.utils;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.ClassPathResource;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

public class WebDriverFactory {
	
	
	
	public WebDriverFactory()
	{
		
	}
	
	  public static WebDriver get(String browser) throws Exception
	  {	
		 
		  WebDriver driver=null;
		  
		  XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		  
			   //WebDriver driver=null;
			   //XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

			     if(browser.contains("chrome"))
			     {
			    	// File inputFile = new ClassPathResource("/home/akshayee/Desktop/chromedriver.exe").getFile();
			    //	 System.setProperty("webdriver.chrome.driver","/home/akshayee/Desktop/chromedriver.exe");

			    	
			    	 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/GridConfig/chromedriver");
					 driver=new ChromeDriver();
					  
				
						 
					 DesiredCapabilities cap= DesiredCapabilities.chrome();
					 cap.setBrowserName(browser);
					 cap.setPlatform(Platform.LINUX);
					 String host=test.getParameter("deviceHost");
					 String port=test.getParameter("devicePort");
					 driver= new RemoteWebDriver(new URL("http://"+host+":"+port+"/wd/hub"),cap);
					 test.getParameter("deviceHost");
			 	
			     }

				 // {
			  /*  	System.setProperty("webdriver.Chrome.driver", "C:\\UsersAdministrator\\Desktop\\AutomationFramework\\iShuttleAutomation\\GridConfig\\chromedriver.exe");
			    DesiredCapabilities cap= DesiredCapabilities.chrome();
			    cap.setBrowserName(browser);
			    cap.setPlatform(Platform.XP);
				driver = new RemoteWebDriver(new URL("http://10.10.10.83:4444/wd/hub"), cap);
			    String host=test.getParameter("deviceHost");
			    String port=test.getParameter("devicePort");
			    driver= new RemoteWebDriver(new URL("http://"+host+":"+port+"/wd/hub"),cap);
			    test.getParameter("deviceHost");
			    }
*/
			    	  
		  else if(browser.contains("firefox"))
		  {
			  
					//driver= new FirefoxDriver();
		              
			  System.setProperty("webdriver.gecko.driver","/home/akshayee/Applications/AutomationFramework/iShuttleAutomation/GridConfig/geckodriver");
			
			  
				driver = new FirefoxDriver();

				 ProfilesIni profile = new ProfilesIni();

                 FirefoxProfile myProfile = profile.getProfile("default");

                 DesiredCapabilities capability = DesiredCapabilities.firefox();

                 capability.setCapability(FirefoxDriver.PROFILE, myProfile );

                 capability.setCapability("marionette", true);

                 capability.setBrowserName("firefox");
			  
		         test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		         
		         capability.setBrowserName(browser);
		         capability.setPlatform(Platform.LINUX); 
		             
		         String host=test.getParameter("deviceHost");
		         String port=test.getParameter("devicePort");
		         driver= new RemoteWebDriver(new URL("http://10.10.10.120:5555/wd/hub"),capability);
		  }
				
	  	
		  
		  if(browser.contains("ie"))
		  {
			  
				System.setProperty("webdriver.ie.driver", "/home/akshayee/Desktop/IEDriverServer.exe");
				driver = new InternetExplorerDriver();	 
			/*
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		  */
		  }
		  return driver;
	  }

}
