package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class ProjectRepositoryPage extends BasePage {

    private String suitePath = "//a[@title='%s']";
    private String alertPopupPath = "//span[contains(text(),'%s')]/ancestor::div[@role='alert']";
    private final String testCaseCreatedSuccessfullyText = "Test case was created successfully!";
    @FindBy(xpath = "//h1[text()=' repository']")
    private WebElement projectRepositoryPageTitle;

    @FindBy(xpath = "//a[@id='create-suite-button']")
    private WebElement addSuiteButton;

    @FindBy(xpath = "//a[@id='create-case-button']")
    private WebElement addCaseButton;

    public String getProjectsRepositoryPageTitle() {
        return waitVisibilityOfElement(projectRepositoryPageTitle).getText();
    }

    @Step("Click on the '+ Suite' button")
    public void clickOnAddSuiteButton() {
        log.info("Click on the '+ Suite' button");
        waitElementToBeClickable(addSuiteButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    @Step("Click on the '+ Case' button")
    public void clickOnAddCaseButton() {
        log.info("Click on the '+ Case' button");
        waitElementToBeClickable(addCaseButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    public boolean isSuiteByNamePresent(String suiteName) {
        log.info("Check suite by name is shown on the Repository page");
        boolean isSuiteNameOnPage;
        By suiteNameInListElement = getElementLocatorByPathAndOneParam(suitePath, suiteName);
        try {
            driver.findElement(suiteNameInListElement).isDisplayed();
            isSuiteNameOnPage = true;
        } catch (NoSuchElementException e) {
            isSuiteNameOnPage = false;
        }
        AllureUtils.takeScreenshot(driver);
        return isSuiteNameOnPage;
    }

    public boolean isAlertOnTestCaseCreatedSuccessfullyAppears() {
        log.info("Check if Alert pop-up appears on successful test case creation");
        By alertElement = getElementLocatorByPathAndOneParam(alertPopupPath, testCaseCreatedSuccessfullyText);
        boolean isAlertAppears = driver.findElement(alertElement).isDisplayed();
        AllureUtils.takeScreenshot(driver);
        return isAlertAppears;
    }

    public String getTextFromCreateTestCaseSuccessAlert() {
        By alertElement = getElementLocatorByPathAndOneParam(alertPopupPath, testCaseCreatedSuccessfullyText);
        return driver.findElement(alertElement).getText();
    }
}
