package com.ishuttle.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Print_Report {
	WebDriver w;
	
  @Test
  public void Report() throws Exception
  {
	  w=new FirefoxDriver();
	  w.navigate().refresh();
	  w.get("file:///home/akshayee/Applications/AutomationFramework/iShuttleAutomation/test-output/extentreport.html");
	  
	  w.navigate().refresh();
	  Thread.sleep(20000);
	  
	  File src=((TakesScreenshot)w).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(src, new File("/home/akshayee/Applications/AutomationFramework/iShuttleAutomation/test-output/extentReport/Report/regression.jpg"));
   //   w.close();
	  
  }
}

