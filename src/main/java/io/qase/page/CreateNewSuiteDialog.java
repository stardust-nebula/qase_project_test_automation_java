package io.qase.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qase.util.AllureUtils;

@Log4j2
public class CreateNewSuiteDialog extends BasePage {

    @FindBy(xpath = "//input[@id='title']")
    private WebElement suiteNameField;

    @FindBy(xpath = "//label[text()='Description']/parent::div/following-sibling::div//p")   //label[text()='Description']/following-sibling::div[contains(@class,'container')]
    private WebElement descriptionField;

    @FindBy(xpath = "//label[text()='Preconditions']/parent::div/following-sibling::div//p")  //label[text()='Preconditions']/following-sibling::div[1]
    private WebElement preconditionsField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createButton;

    private ProjectRepositoryPage projectRepositoryPage = new ProjectRepositoryPage();

    @Step("Open Create suite dialog")
    public CreateNewSuiteDialog openCreateSuiteDialog() {
        projectRepositoryPage.clickOnAddSuiteButton();
        return this;
    }

    @Step("Enter Suite name")
    public CreateNewSuiteDialog enterSuiteName(String suiteName) {
        log.info("Enter suite name: " + suiteName);
        waitVisibilityOfElement(suiteNameField).clear();
        suiteNameField.sendKeys(suiteName);
        return this;
    }

    @Step("Enter Description")
    public CreateNewSuiteDialog enterDescription(String description) {
        log.info("Enter description: " + description);
        waitVisibilityOfElement(descriptionField);
        if (description != null) {
            descriptionField.sendKeys(description);
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Enter Preconditions")
    public CreateNewSuiteDialog enterPreconditions(String preconditions) {
        log.info("Enter Preconditions: " + preconditions);
        waitVisibilityOfElement(preconditionsField);
        if (preconditions != null) {
            preconditionsField.sendKeys(preconditions);
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click on the 'Create' button")
    public void clickCreateButton() {
        log.info("Click on 'Create' button");
        waitElementToBeClickable(createButton).click();
    }
}
