package com.epam.taf.uitest;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.taf.Driver;
import com.epam.taf.Listeners.TestListener;
import com.epam.taf.helpers.DataLoader1;
import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Listeners({ReportPortalTestNGListener.class, TestListener.class})
public abstract class AbstractTest {
    WebDriver driver;
    protected static final Logger LOGGER = Logger.getLogger(AbstractTest.class.getName());
    static DataLoader1 dataloader = new DataLoader1();
    protected static final String UI_URL = dataloader.returnObj().getUiUrl();

    @BeforeMethod
    @Step("Launching Conduit Application")
    public void initialisation() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get(UI_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void logBefore(Method method) {
        LOGGER.info(String.format("STARTING UI %s test", method.getName()));
        LOGGER.info("ASSERTING results");
    }

   @AfterMethod
    public void terminate() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("URL", UI_URL)
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/"
        );

    }
}

