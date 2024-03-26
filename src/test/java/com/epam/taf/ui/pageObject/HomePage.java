package com.epam.taf.ui.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passWord;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath ="//div[@class='error-message-container error']/h3")
    private WebElement loginErrorText;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @Step("Enter Credentials and Tpa on SignIn button")
    public HomePage signingIn (String username , String password){
        userName.sendKeys(username);
        passWord.sendKeys(password);
        loginButton.click();
        return this;
    }
    @Step("Get Error Text")
    public String getErrorText(){
       String errorText = loginErrorText.getText();
       return errorText;
    }

}