package com.epam.taf.Listeners;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import com.epam.taf.uitest.AbstractTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult iTestResult) {
        if (iTestResult.getInstance() instanceof AbstractTest) {
            File screen = saveScreenshot(iTestResult);
            try {
                ReportPortalMessage reportPortalMessage = new ReportPortalMessage(screen, "Screenshot: ");
                ReportPortal.emitLog(reportPortalMessage, "WARN", new Date());
            } catch (IOException e) {
            }
        }
    }

    private static String generateUniqueString() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmssSS");
        Date date = new Date();
        return formatter.format(date) + Thread.currentThread().getName();
    }
    private File saveScreenshot(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver webDriver = ((AbstractTest) currentClass).getDriver();

        File screenCapture = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        try {
            String fileName = System.getProperty("user.dir") + "\\target\\screenshots\\screenshot" + generateUniqueString() + ".png";
            FileUtils.copyFile(screenCapture, new File(fileName));
        } catch (IOException e) {
        }
        return screenCapture;
    }
}
