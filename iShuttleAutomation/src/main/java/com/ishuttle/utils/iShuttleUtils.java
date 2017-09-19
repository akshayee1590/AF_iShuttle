package com.ishuttle.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class iShuttleUtils {
	
    public static int iShuttleMaxElementWait = 3;
	public static int iShuttleMinElementWait = 2;
	
	 /**
     * To wait for the specific element on the page
     * 
     * @param driver -
     * @param element - webelement to wait for to appear
     * @param maxWait - how long to wait for
     * @return boolean - return true if element is present else return false
     */
    public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait,ExtentTest test) {
        boolean statusOfElementToBeReturned = false;
        long startTime = StopWatch.startTime();
        WebDriverWait wait = new WebDriverWait(driver, maxWait);
        try {
            WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                statusOfElementToBeReturned = true;
                test.log(Status.PASS,"Element is displayed:: " + element.toString());
            }
        } catch (Exception ex) {
            statusOfElementToBeReturned = false;
            test.log(Status.FAIL,"Unable to find a element after " + StopWatch.elapsedTime(startTime) + " sec ==> " + element.toString());
        }
        return statusOfElementToBeReturned;
    }
	
	public static void captureAndDisplayScreenShot(WebDriver ldriver, ExtentTest eTest ){
		 String extentReportImage = System.getProperty("user.dir")+ "/test-output/extentReport/screenshots/" + System.currentTimeMillis() + ".jpeg";
		// Take screenshot and store as a file format
		File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File(extentReportImage));
			eTest.log(Status.INFO, "Screenshot from : " + extentReportImage, MediaEntityBuilder.createScreenCaptureFromPath(extentReportImage).build());
		} catch (IOException e)
		{
			System.out.println("Error in the captureAndDisplayScreenShot method: " + e.getMessage());
		}
	}

}
