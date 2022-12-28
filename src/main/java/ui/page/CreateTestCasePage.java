package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class CreateTestCasePage extends BasePage {

    @FindBy(xpath = "//input[@id='title']")
    private WebElement titleField;

    @FindBy(xpath = "//button[@id='save-case']")
    private WebElement saveButton;

    @Step("Enter title for a new test case")
    public CreateTestCasePage enterTestCaseTitle(String testCaseTitle) {
        log.info("Enter title for a new test case");
        waitVisibilityOfElement(titleField).clear();
        titleField.sendKeys(testCaseTitle);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click on the 'Save' button to save a new test case")
    public void clickOnSaveButton() {
        log.info("Click on the 'Save' button to save a new test case");
        waitElementToBeClickable(saveButton).click();
        AllureUtils.takeScreenshot(driver);
    }
}
