package Hexaware.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hexaware.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
	
	  public void onTestStart(ITestResult result) {
	    // not implemented
		  test = extent.createTest(result.getMethod().getMethodName());
		  extentTest.set(test); // unique thread id
	  }

	  
	  public void onTestSuccess(ITestResult result) {
	    // not implemented
		  extentTest.get().log(Status.PASS, "Test Passed");
	  }

	
	  public void onTestFailure(ITestResult result) {
	    // not implemented
		  extentTest.get().fail(result.getThrowable());
			
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	 
	  public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	  
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	 
	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	
	  public void onFinish(ITestContext context) {
	    // not implemented
		  extent.flush();
	  }
}
