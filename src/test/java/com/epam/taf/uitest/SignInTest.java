package com.epam.taf.uitest;
import com.epam.taf.helpers.DataLoader1;
import com.epam.taf.ui.pageObject.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends AbstractTest{

    static DataLoader1 dataloader = new DataLoader1();
    protected static final String User_Name = dataloader.returnObj().getSignInUsername();
    protected static final String Password = dataloader.returnObj().getSignInPassword();

    @Test()
    @Description("Verify Login with Invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify User Login")
    public void submitInvalidCredentialsTest() {
        String errorMessage = new HomePage(getDriver())
                .signingIn(User_Name,Password)
                .getErrorText();
        LOGGER.info(String.format("Expected: Epic sadface: Username and password do not match any user in this service: %S", errorMessage));
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

}


