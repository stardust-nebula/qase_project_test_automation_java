package io.qase.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qase.util.AllureUtils;

@Log4j2
public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='inputEmail']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='inputPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='btnLogin']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@data-qase-test='login-error']")
    private WebElement loginErrorMessage;

    @Step("Open Login page")
    public LoginPage openPage(String url) {
        log.info("Open Login page by link: " + url);
        driver.get(url);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Enter email in the 'email' field")
    public LoginPage enterEmail(String emailValue) {
        log.info("Enter email " + emailValue);
        waitVisibilityOfElement(emailField).clear();
        emailField.sendKeys(emailValue);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Enter password in the 'password' field")
    public LoginPage enterPassword(String passwordValue) {
        log.info("Enter password " + passwordValue);
        waitVisibilityOfElement(passwordField).clear();
        passwordField.sendKeys(passwordValue);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click on the 'Login' button")
    public void clickLoginButton() {
        log.info("Click on 'Login' button");
        waitElementToBeClickable(loginButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    public boolean isLoginErrorMessageDisplays() {
        log.info("Invalid credentials");
        return waitVisibilityOfElement(loginErrorMessage).isDisplayed();
    }
}
