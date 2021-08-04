package com.test.listener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.constant.PropertyConstant;
import com.test.until.ConfigurationUtil;
import com.test.until.DateTimeUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testcase.Core;


import java.io.File;
import java.io.IOException;

public class ExtentListener implements ITestListener {
    ConfigurationUtil props;
    ExtentReports extent;
    ThreadLocal<ExtentTest> tests = new ThreadLocal<>();
    String reportPath;

    public ExtentListener() throws IOException {
        props = ConfigurationUtil.getInstance();
        reportPath = props.getProperty(PropertyConstant.REPORT_PATH);
        File newDir = new File(reportPath);
        if (!newDir.exists()){
            newDir.mkdirs();
        }
        extent = new ExtentReports(reportPath+"/"+ DateTimeUtils.getCurrentDateTime()+"-extent.html",true);
        extent.addSystemInfo("OS architecture",System.getProperty("os.arch"));
        extent.addSystemInfo("OS version",System.getProperty("os.version"));
        extent.addSystemInfo("Java version",System.getProperty("java.version"));
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test start: " + iTestResult.getMethod().getDescription());
        ExtentTest test=extent.startTest(iTestResult.getMethod().getMethodName()+": "+
                DateTimeUtils.getCurrentDateTime(),
                iTestResult.getMethod().getDescription());
        tests.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTest test = tests.get();
        System.out.println("Test success: " + iTestResult.getMethod().getDescription());
        test.log(LogStatus.PASS,"pass");
        String temp = reportPath+"/capture/"+DateTimeUtils.getCurrentDateTime()+".png";
        try {
            Core.takeSnapShot(((Core) iTestResult.getInstance()).getDriver(),temp);
            test.addScreenCapture(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extent.endTest(test);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTest test = tests.get();
        System.out.println("Test fail: " + iTestResult.getMethod().getDescription());
        test.log(LogStatus.FAIL,"fail: "+iTestResult.getThrowable());
        String temp = reportPath+"/capture/"+DateTimeUtils.getCurrentDateTime()+".png";
        try {
            Core.takeSnapShot(((Core) iTestResult.getInstance()).getDriver(),temp);
            test.addScreenCapture(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extent.endTest(test);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skip: " + iTestResult.getMethod().getDescription());
        ExtentTest test = tests.get();
        test.log(LogStatus.SKIP,"skip");
        test.log(LogStatus.SKIP,"skip: "+iTestResult.getThrowable());
        String temp = reportPath+"/capture/"+DateTimeUtils.getCurrentDateTime()+".png";
        try {
            Core.takeSnapShot(((Core) iTestResult.getInstance()).getDriver(),temp);
            test.addScreenCapture(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extent.endTest(test);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("on Start..");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("on finish..");
        extent.flush();

    }


}
