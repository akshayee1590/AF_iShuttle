package com.ishuttle.utils;

import java.io.File;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager 
{
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	ExtentTest logger;
	private static String filePath = "/home/akshayee/Applications/AutomationFramework/iShuttleAutomation/test-output/extentreport.html";
 
	public static ExtentReports GetExtent(){
		if (extent != null)
                  return extent; //avoid creating new instance of html file
		extent = new ExtentReports();	
		extent.attachReporter(getHtmlReporter());
		
		extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Host Name", "Akshayee");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Akshayee Sawant");
       
				return extent;
	}
	
	private static ExtentHtmlReporter getHtmlReporter() {
		
        htmlReporter = new ExtentHtmlReporter(filePath);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
		
        htmlReporter.config().setDocumentTitle("iShuttle Automation Report");
        htmlReporter.config().setReportName("Regression cycle");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
       
        return htmlReporter;
	}
	
	
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
 
}
